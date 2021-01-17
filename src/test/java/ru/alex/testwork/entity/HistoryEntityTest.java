package ru.alex.testwork.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryEntityTest {

	public static final long ID = 1L;
	public static final String SEC_ID = "DDDD";
	public static final Date TRADE_DATE = Date.valueOf("2021-01-14");
	public static final double NUM_TRADES = 2345.8;
	public static final double OPEN = 10.5;
	public static final double CLOSE = 9.3;
	public static final SecuritiesEntity SECURITIES_ENTITY = new SecuritiesEntity();
	public static final int AMOUNT_FIELDS = 7;
	HistoryEntity entity;

	@BeforeEach
	void setEntity() {
		entity = new HistoryEntity();
		entity.setId(ID);
		entity.setSecId(SEC_ID);
		entity.setTradeDate(TRADE_DATE);
		entity.setNumTrades(NUM_TRADES);
		entity.setOpen(OPEN);
		entity.setClose(CLOSE);
		entity.setSecurities(SECURITIES_ENTITY);
	}

	@Test
	void shouldRetrieveValidFields() {
		assertEquals(ID, entity.getId());
		assertEquals(SEC_ID, entity.getSecId());
		assertEquals(TRADE_DATE, entity.getTradeDate());
		assertEquals(NUM_TRADES, entity.getNumTrades());
		assertEquals(OPEN, entity.getOpen());
		assertEquals(CLOSE, entity.getClose());
		assertEquals(SECURITIES_ENTITY, entity.getSecurities());
	}

	@Test
	void shouldRetrieveValidAmountFields() {
		Class<?> xmlClass = entity.getClass();

		assertEquals(AMOUNT_FIELDS, xmlClass.getDeclaredFields().length);
	}

	@Test
	void shouldValidToString() {
		String expected = "HistoryEntity{" +
				"id=" + ID +
				", tradeDate=" + TRADE_DATE +
				", secId='" + SEC_ID + '\'' +
				", numTrades=" + NUM_TRADES +
				", open=" + OPEN +
				", close=" + CLOSE +
				'}';
		assertEquals(expected, entity.toString());
	}
}