package ru.alex.testwork.camelrouters;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.stereotype.Component;
import ru.alex.testwork.entity.Securities;
import ru.alex.testwork.mapper.SecuritiesMapper;
import ru.alex.testwork.camelrouters.xml.securities.SecuritiesListXml;
import ru.alex.testwork.camelrouters.xml.securities.SecuritiesXml;
import ru.alex.testwork.service.impl.SecuritiesServiceImpl;
import ru.alex.testwork.utils.FileFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
//TODO Const
@Component
public class ParseSecuritiesRouter extends RouteBuilder {

	final JaxbDataFormat jaxbListSec;
	final SecuritiesServiceImpl securitiesService;

	public ParseSecuritiesRouter(JaxbDataFormat jaxbListSec, SecuritiesServiceImpl securitiesService) {
		this.jaxbListSec = jaxbListSec;
		this.securitiesService = securitiesService;
	}

	@Override
	public void configure() throws Exception {
		from("direct:parseSecurities").routeId("Route parseSecurities")
				.log("Run parseSecurities ...")
				.process(ParseSecuritiesRouter::fileSecuritiesAmount)
				.choice()
				.when(simple("${body} > 0")).to("direct:fileLoopSecurities")
				.otherwise().log("file securities amount = 0").to("direct:faultSecurities")
				.end()
				.log("Stop parseSecurities")

		;

		//File loop
		from("direct:fileLoopSecurities").routeId("Router fileLoopSecurities")
				.loop(simple("${body}"))
				.pollEnrich("file://inbox/securities?include=securities_[0-9]*.xml&noop=true")
				.to("direct:validXmlSecurities")
				.end()
		;

		//Valid XML
		from("direct:validXmlSecurities").routeId("Route validXmlSecurities")
				.convertBodyTo(String.class)
				.doTry()
				.to("validator:xsd/securities.xsd")
				.doCatch(ValidationException.class)
				.log(LoggingLevel.ERROR, "Failed validation xml Securities")
				.setBody(constant("failed"))
				.end()

				.choice()
				.when(simple("${body} == 'failed'")).to("direct:endFileLoop")
				.otherwise().to("direct:unmarshalSecurities")
				.end()
		;

		// Unmarshal securities
		from("direct:unmarshalSecurities")
				.transform(xpath("//document/data[@id='securities']/rows"))
				.unmarshal(jaxbListSec)
				.process(this::convertListXmlToSecurities)
				.process(this::saveToDB)
				.to("direct:endFileLoop")
		;

		// End file loop
		from("direct:endFileLoop").routeId("Route endFileLoop")
				.log("... file processed")
		;

		// route in case of error
		from("direct:faultSecurities")
				.routeId("Route faultSecurities")
				.transform().body().setBody(constant("stop fault"))
				.log("return: body = stop fault")
				.stop();
	}

	private static void fileSecuritiesAmount(Exchange exchange) {
		FileFinder finder = new FileFinder("inbox/securities", "securities_[0-9]*.xml");
		int amount = finder.done();
		exchange.getIn().setBody(amount, Integer.class);
	}

	@SuppressWarnings("unchecked")
	private void saveToDB(Exchange exchange) {
		List<Securities> securitiesList = exchange.getIn().getBody(ArrayList.class);
		securitiesService.saveAll(securitiesList);
	}

	private void convertListXmlToSecurities(Exchange exchange) {
		List<SecuritiesXml> securitiesXmlList = exchange.getIn().getBody(SecuritiesListXml.class).getSecuritiesXmlList();
		List<Securities> securitiesList = securitiesXmlList.stream()
				.map(SecuritiesMapper::xmlToEntity)
				.collect(Collectors.toList());
		exchange.getIn().setBody(securitiesList);
	}
}
