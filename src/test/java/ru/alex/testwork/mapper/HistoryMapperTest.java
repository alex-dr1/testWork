package ru.alex.testwork.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alex.testwork.dto.HistoryDto;
import ru.alex.testwork.entity.HistoryEntity;
import ru.alex.testwork.entity.SecuritiesEntity;
import ru.alex.testwork.xml.history.HistoryXml;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryMapperTest {

	public static final long ID = 1L;
	public static final String SEC_ID = "DDDD";
	public static final Date TRADE_DATE = Date.valueOf("2021-01-14");
	public static final double NUM_TRADES = 2345.8;
	public static final double OPEN = 10.5;
	public static final double CLOSE = 9.3;
	public static final SecuritiesEntity SECURITIES_ENTITY = new SecuritiesEntity();
	private static final String DATA_PATTERN = "yyyy-MM-dd";
	HistoryEntity entity;
	HistoryDto dto;
	SimpleDateFormat simpleDateFormat;

	@BeforeEach
	void setEntity() {
		simpleDateFormat = new SimpleDateFormat(DATA_PATTERN);

		entity = new HistoryEntity();
		entity.setId(ID);
		entity.setSecId(SEC_ID);
		entity.setTradeDate(TRADE_DATE);
		entity.setNumTrades(NUM_TRADES);
		entity.setOpen(OPEN);
		entity.setClose(CLOSE);
		entity.setSecurities(SECURITIES_ENTITY);

		dto = new HistoryDto(ID,
				simpleDateFormat.format(TRADE_DATE),
				SEC_ID,
				String.valueOf(NUM_TRADES),
				String.valueOf(OPEN),
				String.valueOf(CLOSE));

	}


	@Test
	void xmlToEntity() {
		HistoryXml xml = new HistoryXml();
		xml.setSecId(SEC_ID);
		xml.setTradeDate(simpleDateFormat.format(TRADE_DATE));
		xml.setNumTrades(String.valueOf(NUM_TRADES));
		xml.setOpen(String.valueOf(OPEN));
		xml.setClose(String.valueOf(CLOSE));

		HistoryEntity actualEntity = HistoryMapper.xmlToEntity(xml);

		assertThat(actualEntity)
				.usingRecursiveComparison()
				.ignoringFields("id")
				.ignoringFields("securities")
				.isEqualTo(entity);

	}

	@Test
	void dtoToEntity() {
		HistoryEntity actualEntity = HistoryMapper.dtoToEntity(dto);

		assertThat(actualEntity)
				.usingRecursiveComparison()
				.ignoringFields("id")
				.ignoringFields("securities")
				.isEqualTo(entity);

	}

	@Test
	void entityToDto() {
		assertEquals(dto, HistoryMapper.entityToDto(entity));
	}
}