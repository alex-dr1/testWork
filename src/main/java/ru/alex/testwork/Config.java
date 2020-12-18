package ru.alex.testwork;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

@Configuration
public class Config {

	@Bean
	public UriBuilder getUriIssSecuritiesBuilder() {
		return new DefaultUriBuilderFactory()
				.builder()
				.scheme("https")
				.host("iss.moex.com")
				.path("iss")
				.path("/securities.xml")
				.queryParam("iss.meta","off")
				.queryParam("securities.columns", "id,secid,shortname,regnumber,emitent_title");
	}
}
