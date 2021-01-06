package ru.alex.testwork.config;

import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alex.testwork.domain.xml.securities.SecuritiesListXml;
import ru.alex.testwork.domain.xml.securities.SecuritiesXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Configuration
public class UnmarshalConfig {
	// XML Data Format
	@Bean("jaxbListSec")
	public JaxbDataFormat getJaxbListSecurities() throws JAXBException {

		JaxbDataFormat jaxbListSec = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(SecuritiesListXml.class);
		jaxbListSec.setContext(con);

		return jaxbListSec;
	}

	@Bean("jaxbSec")
	public JaxbDataFormat getJaxbSecurities() throws JAXBException {

		JaxbDataFormat jaxbSec = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(SecuritiesXml.class);
		jaxbSec.setContext(con);

		return jaxbSec;
	}
}
