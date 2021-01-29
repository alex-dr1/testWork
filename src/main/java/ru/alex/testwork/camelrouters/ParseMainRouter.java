package ru.alex.testwork.camelrouters;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ParseMainRouter extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("direct:parse").routeId("Route main parse")
				.setProperty("processed", constant(""))
				.log("Run main parse ...")
				.to("direct:parseSecurities")
				.to("direct:parseHistory")
				.setBody().exchangeProperty("processed")
		;
	}
}
