package ru.alex.testwork.domain.xml.history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryXmlTest {

	public static final String CLOSE = "200.3";
	public static final String NUM_TRADES = "618";
	public static final String OPEN = "42.23";
	public static final String SEC_ID = "AAAA";
	public static final String TRADE_DATE = "2021-01-13";
	public static final int AMOUNT_FIELDS = 5;
	HistoryXml historyXml;

	@BeforeEach
	void setUp() {
		historyXml = new HistoryXml();
		historyXml.setClose(CLOSE);
		historyXml.setNumTrades(NUM_TRADES);
		historyXml.setOpen(OPEN);
		historyXml.setSecId(SEC_ID);
		historyXml.setTradeDate(TRADE_DATE);
	}

	@Test
	void shouldRetrieveValidFieldClose() {
		assertEquals(CLOSE, historyXml.getClose());
	}

	@Test
	void shouldRetrieveValidFieldNumTrades() {
		assertEquals(NUM_TRADES, historyXml.getNumTrades());
	}

	@Test
	void shouldRetrieveValidFieldOpen() {
		assertEquals(OPEN, historyXml.getOpen());
	}

	@Test
	void shouldRetrieveValidFieldSecId() {
		assertEquals(SEC_ID, historyXml.getSecId());
	}

	@Test
	void shouldRetrieveValidFieldTradeDate() {
		assertEquals(TRADE_DATE, historyXml.getTradeDate());
	}

	@Test
	void shouldRetrieveValidAmountFields() {
		Class<?> xmlClass = historyXml.getClass();
		assertEquals(AMOUNT_FIELDS, xmlClass.getDeclaredFields().length);
	}

	@Test
	void shouldRetrieveValidToString() {
		String expected = "HistoryXml{" +
				"tradeDate='" + TRADE_DATE + '\'' +
				", secId='" + SEC_ID + '\'' +
				", numTrades='" + NUM_TRADES + '\'' +
				", open='" + OPEN + '\'' +
				", close='" + CLOSE + '\'' +
				'}';
		assertEquals(expected, historyXml.toString());
	}
}