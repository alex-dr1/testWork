package ru.alex.testwork.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alex.testwork.domain.model.History;
import ru.alex.testwork.domain.model.Securities;
import ru.alex.testwork.domain.xml.history.HistoryXml;
import ru.alex.testwork.domain.xml.securities.SecuritiesXml;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class HistoryEntityTest {

	public static final long ID = 1L;
	public static final String SEC_ID = "DDDD";
	public static final Date TRADE_DATE = Date.valueOf("2021-01-14");
	public static final double NUM_TRADES = 2345.8;
	public static final double OPEN = 10.5;
	public static final double CLOSE = 9.3;
	public static final SecuritiesEntity SECURITIES_ENTITY = new SecuritiesEntity();
	public static final int AMOUNT_FIELDS = 7;
	private static final String DATA_PATTERN = "yyyy-MM-dd";
	HistoryEntity entity;
	SimpleDateFormat simpleDateFormat;

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

		simpleDateFormat = new SimpleDateFormat(DATA_PATTERN);
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
	void shouldRetrieveValidHistoryEntityFromMethodToEntityModel() {
		HistoryEntity modelToEntity = HistoryEntity.toEntity(
				new History(ID,
				simpleDateFormat.format(TRADE_DATE),
				SEC_ID,
				String.valueOf(NUM_TRADES),
				String.valueOf(OPEN),
				String.valueOf(CLOSE))
		);

		assertEquals(entity.getTradeDate(), modelToEntity.getTradeDate());
		assertEquals(entity.getSecId(), modelToEntity.getSecId());
		assertEquals(entity.getNumTrades(), modelToEntity.getNumTrades());
		assertEquals(entity.getOpen(), modelToEntity.getOpen());
		assertEquals(entity.getClose(), modelToEntity.getClose());
	}

	@Test
	void shouldRetrieveValidHistoryEntityFromMethodToEntityXml() {
		HistoryXml xml = new HistoryXml();
		xml.setSecId(SEC_ID);
		xml.setTradeDate(simpleDateFormat.format(TRADE_DATE));
		xml.setNumTrades(String.valueOf(NUM_TRADES));
		xml.setOpen(String.valueOf(OPEN));
		xml.setClose(String.valueOf(CLOSE));

		HistoryEntity xmlToEntity = HistoryEntity.toEntity(xml);

		assertEquals(entity.getTradeDate(), xmlToEntity.getTradeDate());
		assertEquals(entity.getSecId(), xmlToEntity.getSecId());
		assertEquals(entity.getNumTrades(), xmlToEntity.getNumTrades());
		assertEquals(entity.getOpen(), xmlToEntity.getOpen());
		assertEquals(entity.getClose(), xmlToEntity.getClose());
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