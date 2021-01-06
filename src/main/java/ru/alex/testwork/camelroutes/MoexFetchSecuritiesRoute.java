package ru.alex.testwork.camelroutes;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alex.testwork.domain.Securities;
import ru.alex.testwork.domain.xml.securities.SecuritiesXml;

@Component
public class MoexFetchSecuritiesRoute extends RouteBuilder {

	@Autowired
	private final JaxbDataFormat jaxbSec;

	public MoexFetchSecuritiesRoute(JaxbDataFormat jaxbSec) {
		this.jaxbSec = jaxbSec;
	}

	private final Processor convertXmlToSecurities = exchange -> {
		SecuritiesXml xml = exchange.getIn().getBody(SecuritiesXml.class);
		if(xml == null){
			exchange.getIn().setBody(null);
		} else {
			Securities securities = new Securities();
			securities.setId(xml.getId());
			securities.setSecId(xml.getSecId());
			securities.setName(xml.getName());
			securities.setRegNumber(xml.getRegNumber());
			securities.setEmitentTitle(xml.getEmitentTitle());
			exchange.getIn().setBody(securities);
		}
	};

	@Override
	public void configure() throws Exception {
		// Http request to iss.moex.com
		from("direct:fetchSecurities").routeId("Route fetchSecurities")
				.setProperty("secId", simple("${body}"))
				.setHeader("secId", exchangeProperty("secId"))
				.setHeader(Exchange.HTTP_QUERY, simple("iss.meta=off&securities.columns=id,secid,name,regnumber,emitent_title&q=${headers.secId}"))
				//https://iss.moex.com/iss/securities.xml?iss.meta=off&securities.columns=id,secid,name,regnumber,emitent_title&q=AFKS
				.doTry()
				.to("https://iss.moex.com/iss/securities.xml?httpMethod=GET")
				.doCatch(CamelException.class)
				.log(LoggingLevel.ERROR, "${exchangeProperty.CamelExceptionCaught}")
				.to("direct:fault")
				.end()
				.to("direct:validXml");

		//Valid XML
		from("direct:validXml").routeId("Route validXml")
				.convertBodyTo(String.class)
				.doTry()
				.to("validator:xsd/securities.xsd")
				.doCatch(ValidationException.class)
				.log(LoggingLevel.ERROR, "Failed validation xml")
				.to("direct:fault")
				.end().to("direct:unmarshal");

		//Unmarshal
		from("direct:unmarshal").routeId("Route unmarshal")
				.setHeader("secId", exchangeProperty("secId"))
				.transform(xpath("//document/data[@id='securities']/rows/row[@secid=function:simple('${headers.secId}')]"))
				// TODO DELETE ---------------------------------
				.convertBodyTo(String.class)
				.wireTap("direct:saveToFile")
				// TODO DELETE ---------------------------------
				.unmarshal(jaxbSec)
				.process(convertXmlToSecurities)
		;

		// TODO DELETE ---------------------------------
		from("direct:saveToFile").routeId("saveToFile")
				.process(exchange -> {
					String body = exchange.getIn().getBody(String.class) + "\n";
					exchange.getIn().setBody(body);
				})
				.to("file:outdir?fileName=securities.xml&fileExist=Append")
				.stop();
		// TODO DELETE ---------------------------------

		// route in case of error
		from("direct:fault")
				.routeId("Route fault")
				.transform().body().setBody(constant(null))
				.log("return: body = null")
				.stop();
	}
}
