package ru.alex.testwork.config;

import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alex.testwork.domain.xml.securities.SecuritiesListXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Configuration
public class UnmarshalConfig {
	// XML Data Format
	@Bean("jaxbSec")
	public JaxbDataFormat getJaxbSecurities() throws JAXBException {

		JaxbDataFormat jaxbSec = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(SecuritiesListXml.class);
		jaxbSec.setContext(con);

		return jaxbSec;
	}
}
