package ru.alex.testwork.configuration;

import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alex.testwork.xml.history.HistoryListXml;
import ru.alex.testwork.xml.securities.SecuritiesXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Configuration
public class UnmarshalConfig {
	// XML Data Format
	@Bean("jaxbListSec")
	public JaxbDataFormat getJaxbListSecurities() throws JAXBException {

		JaxbDataFormat jaxbListSec = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(ru.alex.testwork.xml.securities.SecuritiesListXml.class);
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

	@Bean("jaxbListHis")
	public JaxbDataFormat getJaxbListHistory() throws JAXBException {

		JaxbDataFormat jaxbListHis = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(HistoryListXml.class);
		jaxbListHis.setContext(con);

		return jaxbListHis;
	}
}
