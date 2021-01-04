package ru.alex.testwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alex.testwork.domain.History;
import ru.alex.testwork.domain.Securities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainTest {

	History history1;
	History history2;
	History history3;

	Securities securities;

	@BeforeEach
	void setUp() {
		history1 = new History(0L, Date.valueOf("2021-01-01"), "shortname", "AFKS", 101, 200.6, 300.5);
		history2 = new History(2L, Date.valueOf("2021-01-01"), "shortname", "AFKS", 102, 203.6, 330.5);
		history3 = new History(1L, Date.valueOf("2021-01-01"), "shortname", "AFKS", 103, 204.6, 333.5);

		securities = new Securities(0L, "AFKS", "regNumer", "blabla", "emitent");
	}

	@Test
	void shouldBeOneEntry() {
		Set<History> historySet = new HashSet<>();

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
