package ru.alex.testwork.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alex.testwork.domain.Securities;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class MoexServiceImplTest {

	Securities securities;

	@Autowired
	MoexService moexService;

	@BeforeEach
	void setUp() {
		securities = new Securities(2699L, "AFKS", "1-05-01669-A", "АФК \"Система\" ПАО ао", "ПУБЛИЧНОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО \"АКЦИОНЕРНАЯ ФИНАНСОВАЯ КОРПОРАЦИЯ \"СИСТЕМА\"");
	}

	@Test
	void shouldFetchSecurities(){
		//TODO MOCK
		System.out.println("> " + moexService.fetchSecuritiesBySecId("BISVP"));
		assertNull(null);
	}

}

