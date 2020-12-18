package ru.alex.testwork.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecuritiesIssImplTest {

	@Autowired
	UriBuilder uriBuilder;

	SecuritiesIssImpl iss;

	@BeforeEach
	void setup(){
		iss = new SecuritiesIssImpl(uriBuilder);
	}

	/*
	* <document>
		<data id="securities">
			<rows>
				<row id="271347881" secid="ENPG" shortname="ЭН+ГРУП ао" regnumber="1-01-16625-A" emitent_title="Международная компания публичное акционерное общество "ЭН+ ГРУП""/>
			</rows>
		</data>
	</document>
	* */
	@Test
	@DisplayName("fetchXML")
	void test3() throws Exception {
		String secID = "ENPG";
		String xmlSecurities = iss.getXMLSecurities(secID);
		System.out.println(xmlSecurities);
	}

	@Test
	@DisplayName("fetchXMLa")
	void test3a() throws Exception {
		String secID = "AFKS";
		String xmlSecurities = iss.getXMLSecurities(secID);
		System.out.println(xmlSecurities);
	}
}