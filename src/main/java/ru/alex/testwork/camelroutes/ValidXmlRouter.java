package ru.alex.testwork.camelroutes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class ValidXmlRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:valid-xml")
				.pollEnrich("file:inbox?noop=true")
				.to("log:log2?showAll=true")
				.choice()
				.when(header("CamelFileName").endsWith(".xml"))
				.to("log:log3?showAll=true")
				.transform(xpath("//document/data/@id", String.class))
				.to("log:log4?showAll=true")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
				.setBody(simple("id = ${body}"))
				.otherwise()
				.to("log:log5?showAll=true")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
				.setBody(constant(""))
		;
	}
}
