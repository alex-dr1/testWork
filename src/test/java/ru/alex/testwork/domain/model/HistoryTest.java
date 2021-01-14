package ru.alex.testwork.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alex.testwork.domain.entity.HistoryEntity;
import ru.alex.testwork.domain.entity.SecuritiesEntity;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

	public static final long ID = 1L;
	public static final String TRADE_DATE = "2021-01-13";
	public static final String SEC_ID = "HHHH";
	public static final String NUM_TRADES = "123.4";
	public static final String OPEN = "203.7";
	public static final String CLOSE = "199.8";
	public static final int AMOUNT_FIELDS = 7;
	History history;

	@BeforeEach
	void setHistory() {
		history = new History(ID, TRADE_DATE, SEC_ID, NUM_TRADES, OPEN, CLOSE);
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
	void shouldRetrieveValidHistoryFromMethodToModel() {
		HistoryEntity entity = new HistoryEntity();
		entity.setId(ID);
		entity.setTradeDate(Date.valueOf(TRADE_DATE));
		entity.setSecId(SEC_ID);
		entity.setNumTrades(Double.parseDouble(NUM_TRADES));
		entity.setOpen(Double.parseDouble(OPEN));
		entity.setClose(Double.parseDouble(CLOSE));
		assertEquals(history, History.toModel(entity));
	}

	@Test
	void shouldValidToString(){
		String expected = "History{" +
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