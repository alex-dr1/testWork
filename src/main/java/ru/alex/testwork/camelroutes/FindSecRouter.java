package ru.alex.testwork.camelroutes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FindSecRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:findsecurities")
				.to("log:LOGGER-FINDER-SEC");
	}
}
