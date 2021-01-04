package ru.alex.testwork.camelroutes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.stereotype.Component;
import ru.alex.testwork.domain.Securities;
import ru.alex.testwork.domain.xml.securities.SecuritiesListXml;
import ru.alex.testwork.domain.xml.securities.SecuritiesXml;

import java.util.List;
import java.util.Optional;

@Component
public class MoexFetchSecuritiesRoute extends RouteBuilder {

	private final JaxbDataFormat jaxbSec;

	public MoexFetchSecuritiesRoute(JaxbDataFormat jaxbSec) {
		this.jaxbSec = jaxbSec;
	}

	@Override
	public void configure() throws Exception {
		from("direct:run-fetch-sec")
				.setProperty("secId", simple("${body}"))
				.setHeader("secId", simple("${body}"))
				.setHeader(Exchange.HTTP_QUERY, simple("iss.meta=off&securities.columns=id,secid,name,regnumber,emitent_title&q=${headers.secId}"))
//						https://iss.moex.com/iss/securities.xml?iss.meta=off&securities.columns=id,secid,name,regnumber,emitent_title&q=AFKS
				.to("http://iss.moex.com/iss/securities.xml?httpMethod=GET")
//				TODO
//						CamelHttpResponseCode=200  &&  CamelHttpResponseText=OK
//						Valid XML
				.to("log:SET_HTTP?showAll=true&multiline=true")
				.transform(xpath("//document/data[@id='securities']/rows"))
				.unmarshal(jaxbSec)
				.process(exchange -> {

					List<SecuritiesXml> securitiesXmlList = exchange
							.getIn()
							.getBody(SecuritiesListXml.class)
							.getSecuritiesXmlList();

					String secId = exchange.getProperty("secId", String.class);

					Optional<SecuritiesXml> first = securitiesXmlList.stream()
							.filter(securitiesXml -> securitiesXml.getSecId().equals(secId))
							.findFirst();
					first.ifPresentOrElse(securitiesXml -> {
								Securities securities = new Securities();
								securities.setId(securitiesXml.getId());
								securities.setSecId(securitiesXml.getSecId());
								securities.setName(securitiesXml.getName());
								securities.setRegNumber(securitiesXml.getRegNumber());
								securities.setEmitentTitle(securitiesXml.getEmitentTitle());
								exchange.getIn().setBody(securities);
							},
							() -> exchange.getIn().setBody(null));
				})
		;
	}
}
