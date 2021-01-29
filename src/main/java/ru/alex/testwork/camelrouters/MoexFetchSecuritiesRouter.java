package ru.alex.testwork.camelrouters;

import org.apache.camel.CamelException;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alex.testwork.camelrouters.xml.securities.SecuritiesXml;
import ru.alex.testwork.mapper.SecuritiesMapper;

//TODO Const
@Component
public class MoexFetchSecuritiesRouter extends RouteBuilder {

	@Autowired
	private final JaxbDataFormat jaxbSec;

	public MoexFetchSecuritiesRouter(JaxbDataFormat jaxbSec) {
		this.jaxbSec = jaxbSec;
	}

	@Override
	public void configure() throws Exception {
		// Http request to iss.moex.com
		from("direct:fetchSecuritiesMoexService").routeId("Route fetchSecuritiesMoexService")
				.setProperty("secId", simple("${body}"))
				.setHeader("secId", exchangeProperty("secId"))
				.setHeader(Exchange.HTTP_QUERY, simple("iss.meta=off&securities.columns=secid,name,regnumber,emitent_title&q=${headers.secId}"))
				.doTry()
				.to("https://iss.moex.com/iss/securities.xml?httpMethod=GET")
				.convertBodyTo(String.class)
				.doCatch(CamelException.class)
				.log(LoggingLevel.ERROR, "${exchangeProperty.CamelExceptionCaught}")
				.setBody(constant("error"))
				.end()

				.choice()
				.when(simple("${body} == 'error'")).to("direct:returnNull")
				.otherwise().to("direct:validXmlMoexService")
				.end()
		;

		//Valid XML
		from("direct:validXmlMoexService").routeId("Route validXmlMoexService")
				.convertBodyTo(String.class)
				.doTry()
				.to("validator:xsd/securities.xsd")
				.doCatch(ValidationException.class)
				.log(LoggingLevel.ERROR, "MoexService: Failed validation xml")
				.setBody(constant("error"))
				.end()

				.choice()
				.when(simple("${body} == 'error'")).to("direct:returnNull")
				.otherwise().to("direct:unmarshalMoexService")
				.end()
		;

		//Unmarshal
		from("direct:unmarshalMoexService").routeId("Route unmarshalMoexService")
				.setHeader("secId", exchangeProperty("secId"))
				.transform(xpath("//document/data[@id='securities']/rows/row[@secid=function:simple('${headers.secId}')]"))
				.unmarshal(jaxbSec)
				.process(MoexFetchSecuritiesRouter::convertXmlToSecuritiesEntity)
		;

		// route return body = null
		from("direct:returnNull")
				.routeId("Route returnNull")
				.transform().body().setBody(constant(null))
				.log("return: body = null")
//				.stop()
		;
	}

	private static void convertXmlToSecuritiesEntity(Exchange exchange) {
		SecuritiesXml xml = exchange.getIn().getBody(SecuritiesXml.class);
		if (xml == null) {
			exchange.getIn().setBody(null);
		} else {
			exchange.getIn().setBody(SecuritiesMapper.xmlToEntity(xml));
		}
	}
}
