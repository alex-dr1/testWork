package ru.alex.testwork.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryDtoTest {

	public static final long ID = 1L;
	public static final String TRADE_DATE = "2021-01-13";
	public static final String SEC_ID = "HHHH";
	public static final String NUM_TRADES = "123.4";
	public static final String OPEN = "203.7";
	public static final String CLOSE = "199.8";
	public static final int AMOUNT_FIELDS = 6;
	HistoryDto history;

	@BeforeEach
	void setHistory() {
		history = new HistoryDto(ID, TRADE_DATE, SEC_ID, NUM_TRADES, OPEN, CLOSE);
	}

	@Test
	void shouldRetrieveValidFields() {
		assertEquals(ID,history.getId());
		assertEquals(TRADE_DATE,history.getTradeDate());
		assertEquals(SEC_ID,history.getSecId());
		assertEquals(NUM_TRADES,history.getNumTrades());
		assertEquals(OPEN,history.getOpen());
		assertEquals(CLOSE,history.getClose());
	}

	@Test
	void shouldRetrieveValidAmountFields() {
		Class<?> xmlClass = history.getClass();
		assertEquals(AMOUNT_FIELDS, xmlClass.getDeclaredFields().length);
	}

	@Test
	void shouldValidToString(){
		String expected = "HistoryDto{" +
				"id=" + ID +
				", tradeDate='" + TRADE_DATE + '\'' +
				", secId='" + SEC_ID + '\'' +
				", numTrades='" + NUM_TRADES + '\'' +
				", open='" + OPEN + '\'' +
				", close='" + CLOSE + '\'' +
				'}';

		assertEquals(expected, history.toString());
	}
}