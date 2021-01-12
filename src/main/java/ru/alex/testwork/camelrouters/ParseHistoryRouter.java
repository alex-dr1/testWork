package ru.alex.testwork.camelrouters;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.stereotype.Component;
import ru.alex.testwork.domain.entity.HistoryEntity;
import ru.alex.testwork.domain.xml.history.HistoryListXml;
import ru.alex.testwork.domain.xml.history.HistoryXml;
import ru.alex.testwork.service.HistoryService;
import ru.alex.testwork.utils.FileFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ParseHistoryRouter extends RouteBuilder {

	final JaxbDataFormat jaxbListHis;
	final HistoryService historyService;

	public ParseHistoryRouter(JaxbDataFormat jaxbListHis, HistoryService historyService) {
		this.jaxbListHis = jaxbListHis;
		this.historyService = historyService;
	}

	Processor fileHistoryAmount = exchange -> {
		FileFinder finder = new FileFinder("inbox/history", "history_[0-9]*.xml");
		int amount = finder.done();
		exchange.getIn().setBody(amount, Integer.class);
	};

	Processor covertListXmlToHistory = exchange -> {
		List<HistoryXml> historyXmlList = exchange.getIn().getBody(HistoryListXml.class).getHistoryXmlList();
		List<HistoryEntity> historyList = historyXmlList.stream()
				.filter(historyXml -> !historyXml.getNumTrades().equals("0"))
				.map(convertHistoryXmlToHistory())
				.collect(Collectors.toList());
		exchange.getIn().setBody(historyList);
	};

	private Function<HistoryXml, HistoryEntity> convertHistoryXmlToHistory() {
		return HistoryEntity::toEntity;
	}

	@Override
	public void configure() throws Exception {
		from("direct:parseHistory").routeId("Route parseHistory")
				.log("Run parseHistory ...")
				.process(fileHistoryAmount)
				.choice()
				.when(simple("${body} > 0")).to("direct:fileLoopHistory")
				.otherwise().log("file history amount = 0").to("direct:faultHistory")
				.end()
				.log("Stop parseHistory ...")
		;
		//File loop
		from("direct:fileLoopHistory").routeId("Router fileLoopHistory")
				.loop(simple("${body}"))
				.pollEnrich("file://inbox/history?include=history_[0-9]*.xml&noop=true")
				.to("direct:validXmlHistory")
				.end()
		;

		//Valid XML
		from("direct:validXmlHistory").routeId("Route validXmlHistory")
				.convertBodyTo(String.class)
				.doTry()
				.to("validator:xsd/history.xsd")
				.doCatch(ValidationException.class)
				.log(LoggingLevel.ERROR, "Failed validation xml History")
				.to("direct:faultHistory")
				.end().to("direct:unmarshalHistory")
		;
		// Unmarshal history
		from("direct:unmarshalHistory")
				.transform(xpath("//document/data[@id='history']/rows"))
				.unmarshal(jaxbListHis)
				.process(covertListXmlToHistory)
				.process(exchange -> {
					List<HistoryEntity> historyList = exchange.getIn().getBody(ArrayList.class);
					//TODO Insert to DB
					historyList.forEach(historyService::save);
				})
				.setBody().simple("stop parseHistory")
		;

		// route in case of error
		from("direct:faultHistory")
				.routeId("Route faultHistory")
				.transform().body().setBody(constant("stop fault"))
				.log("return: body = stop fault")
				.stop();
	}

}
