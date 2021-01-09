package ru.alex.testwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alex.testwork.domain.entity.HistoryEntity;
import ru.alex.testwork.domain.entity.SecuritiesEntity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainTest {

	HistoryEntity history1;
	HistoryEntity history2;
	HistoryEntity history3;

	SecuritiesEntity securities;

	@BeforeEach
	void setUp() {
		history1 = new HistoryEntity();
		history1.setId(0L);
		history1.setTradeDate(Date.valueOf("2021-01-01"));
		history1.setSecId("AFKS");
		history1.setNumTrades(101);
		history1.setOpen(200.6);
		history1.setClose(300.5);

		history2 = new HistoryEntity();
		history2.setId(2L);
		history2.setTradeDate(Date.valueOf("2021-01-01"));
		history2.setSecId("AFKS");
		history2.setNumTrades(102);
		history2.setOpen(203.6);
		history2.setClose(330.5);

		history3 = new HistoryEntity();
		history3.setId(0L);
		history3.setTradeDate(Date.valueOf("2021-01-01"));
		history3.setSecId("AFKS");
		history3.setNumTrades(103);
		history3.setOpen(204.6);
		history3.setClose(333.5);

		securities = new SecuritiesEntity();
		securities.setId(0L);
		securities.setSecId("AFKS");
		securities.setRegNumber("regNumer");
		securities.setName("blabla");
		securities.setEmitentTitle("emitent");
	}

	@Test
	void shouldBeOneEntry() {
		Set<HistoryEntity> historySet = new HashSet<>();

		historySet.add(history1);
		historySet.add(history2);
		historySet.add(history3);

		assertEquals(1, historySet.size());
	}

	@Test
	void shouldBeOneHistory(){
		securities.addHistory(history1);
		securities.addHistory(history2);
		securities.addHistory(history3);

		assertEquals(1, securities.getHistorySet().size());
	}

	@Test
	void shouldBeNullHistory(){
		securities.addHistory(history1);
		securities.addHistory(history2);
		securities.addHistory(history3);

		securities.removeHistory(history1);

		assertEquals(0, securities.getHistorySet().size());
	}
}
