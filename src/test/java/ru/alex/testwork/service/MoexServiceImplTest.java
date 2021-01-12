package ru.alex.testwork.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alex.testwork.domain.entity.SecuritiesEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class MoexServiceImplTest {

	SecuritiesEntity securities;

	@Autowired
	MoexService moexService;

	@BeforeEach
	void setUp() {
		securities = new SecuritiesEntity();
		securities.setId(2699L);
		securities.setSecId("AFKS");
		securities.setRegNumber("1-05-01669-A");
		securities.setName("АФК \"Система\" ПАО ао");
		securities.setEmitentTitle("ПУБЛИЧНОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО \"АКЦИОНЕРНАЯ ФИНАНСОВАЯ КОРПОРАЦИЯ \"СИСТЕМА\"");
	}

	@Test
	void shouldFetchSecurities(){
		//TODO MOCK
		SecuritiesEntity sec = moexService.fetchSecuritiesBySecId("AFKS");
		System.out.println("> " + sec);
		assertEquals(securities, sec);
	}

}

