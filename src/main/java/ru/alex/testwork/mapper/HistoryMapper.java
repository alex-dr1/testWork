package ru.alex.testwork.mapper;

import ru.alex.testwork.controller.dto.HistoryDto;
import ru.alex.testwork.entity.History;
import ru.alex.testwork.camelrouters.xml.history.HistoryXml;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class HistoryMapper {

	//TODO Mapper to bean

	private static final String DATA_PATTERN = "yyyy-MM-dd";

	public static History xmlToEntity(HistoryXml xml) {
		History history = new History();
		history.setSecId(xml.getSecId());
		history.setTradeDate(Date.valueOf(xml.getTradeDate()));
		history.setNumTrades(Double.parseDouble(xml.getNumTrades()));
		history.setOpen(Double.parseDouble(xml.getOpen()));
		history.setClose(Double.parseDouble(xml.getClose()));
		return history;
	}

	public static History dtoToEntity(HistoryDto dto) {
		History history = new History();
		history.setId(dto.getId());
		history.setSecId(dto.getSecId());
		history.setTradeDate(Date.valueOf(dto.getTradeDate()));
		history.setNumTrades(Double.parseDouble(dto.getNumTrades()));
		history.setOpen(Double.parseDouble(dto.getOpen()));
		history.setClose(Double.parseDouble(dto.getClose()));
		return history;
	}

	public static HistoryDto entityToDto(History entity) {
		// TODO to bean
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATA_PATTERN);

		return new HistoryDto(entity.getId(),
				simpleDateFormat.format(entity.getTradeDate()),
				entity.getSecId(),
				String.valueOf(entity.getNumTrades()),
				String.valueOf(entity.getOpen()),
				String.valueOf(entity.getClose()));
	}
}
