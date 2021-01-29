package ru.alex.testwork.camelrouters;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.stereotype.Component;
import ru.alex.testwork.camelrouters.xml.history.HistoryListXml;
import ru.alex.testwork.camelrouters.xml.history.HistoryXml;
import ru.alex.testwork.entity.History;
import ru.alex.testwork.exception.SecuritiesBySecIdNotFoundException;
import ru.alex.testwork.mapper.HistoryMapper;
import ru.alex.testwork.service.impl.HistoryServiceImpl;
import ru.alex.testwork.utils.FileFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//TODO Const
@Component
public class ParseHistoryRouter extends RouteBuilder {

	final JaxbDataFormat jaxbListHis;
	final HistoryServiceImpl historyService;

	public ParseHistoryRouter(JaxbDataFormat jaxbListHis, HistoryServiceImpl historyService) {
		this.jaxbListHis = jaxbListHis;
		this.historyService = historyService;
	}

	@Override
	public void configure() throws Exception {
		from("direct:parseHistory").routeId("Route parseHistory")
				.log("Run parseHistory ...")
				.process(ParseHistoryRouter::fileHistoryAmount)
				.choice()
				.when(simple("${body} > 0")).to("direct:fileLoopHistory")
				.otherwise().log("file history amount = 0").to("direct:faultHistory")
				.end()
				.log("Stop parseHistory")
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
				.setBody(constant("failed"))
				.end()

				.choice()
				.when(simple("${body} == 'failed'")).to("direct:endFileLoop")
				.otherwise().to("direct:unmarshalHistory")
				.end()
		;
		// Unmarshal history
		from("direct:unmarshalHistory")
				.transform(xpath("//document/data[@id='history']/rows"))
				.unmarshal(jaxbListHis)
				.process(this::covertListXmlToHistory)
				.process(this::saveToDB)
				.end()
				.to("direct:endFileLoop")
		;
		// End file loop
		from("direct:endFileLoop").routeId("Route endFileLoop")
				.log("... file processed")
		;

		// route in case of error
		from("direct:faultHistory")
				.routeId("Route faultHistory")
				.transform().body().setBody(constant("stop fault"))
				.log("return: body = stop fault")
				.stop();
	}

	private static void fileHistoryAmount(Exchange exchange) {
		FileFinder finder = new FileFinder("inbox/history", "history_[0-9]*.xml");
		int amount = finder.done();
		exchange.getIn().setBody(amount, Integer.class);
	}

	@SuppressWarnings("unchecked")
	private void saveToDB(Exchange exchange) {
		List<History> historyList = exchange.getIn().getBody(ArrayList.class);
		historyList.forEach(historyService::saveImport);
	}

	private void covertListXmlToHistory(Exchange exchange) {
		List<HistoryXml> historyXmlList = exchange.getIn().getBody(HistoryListXml.class).getHistoryXmlList();
		List<History> historyList = historyXmlList.stream()
				.filter(historyXml -> !historyXml.getNumTrades().equals("0"))
				.map(HistoryMapper::xmlToEntity)
				.collect(Collectors.toList());
		exchange.getIn().setBody(historyList);
	}
}
