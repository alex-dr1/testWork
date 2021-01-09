package ru.alex.testwork.camelroutes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ParseMainRouter extends RouteBuilder {
	@Override
	public void configure() throws Exception {
from("direct:parse").routeId("Route main parse")
		.log("Run main parse ...")
		.to("direct:parseSecurities")
		.to("direct:parseHistory")
		.setBody(constant("stop main"));
	}
}
