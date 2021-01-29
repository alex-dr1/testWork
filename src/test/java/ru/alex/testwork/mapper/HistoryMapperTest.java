package ru.alex.testwork.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alex.testwork.controller.dto.HistoryDto;
import ru.alex.testwork.entity.History;
import ru.alex.testwork.entity.Securities;
import ru.alex.testwork.camelrouters.xml.history.HistoryXml;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryMapperTest {

	public static final long ID = 1L;
	public static final String SEC_ID = "DDDD";
	public static final LocalDate TRADE_DATE = LocalDate.parse("2021-01-14");
	public static final double NUM_TRADES = 2345.8;
	public static final double OPEN = 10.5;
	public static final double CLOSE = 9.3;
	public static final Securities SECURITIES_ENTITY = new Securities();
	History entity;
	HistoryDto dto;

	@BeforeEach
	void setEntity() {

		entity = new History();
		entity.setId(ID);
		entity.setSecId(SEC_ID);
		entity.setTradeDate(TRADE_DATE);
		entity.setNumTrades(NUM_TRADES);
		entity.setOpen(OPEN);
		entity.setClose(CLOSE);
		entity.setSecurities(SECURITIES_ENTITY);

		dto = new HistoryDto(ID,
				TRADE_DATE.toString(),
				SEC_ID,
				String.valueOf(NUM_TRADES),
				String.valueOf(OPEN),
				String.valueOf(CLOSE));
	}


	@Test
	void xmlToEntity() {
		HistoryXml xml = new HistoryXml();
		xml.setSecId(SEC_ID);
		xml.setTradeDate(TRADE_DATE.toString());
		xml.setNumTrades(String.valueOf(NUM_TRADES));
		xml.setOpen(String.valueOf(OPEN));
		xml.setClose(String.valueOf(CLOSE));

		History actualEntity = HistoryMapper.xmlToEntity(xml);

		assertThat(actualEntity)
				.usingRecursiveComparison()
				.ignoringFields("id")
				.ignoringFields("securities")
				.isEqualTo(entity);
	}

	@Test
	void dtoToEntity() {
		History actualEntity = HistoryMapper.dtoToEntity(dto);

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