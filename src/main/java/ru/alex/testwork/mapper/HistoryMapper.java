package ru.alex.testwork.mapper;

import ru.alex.testwork.camelrouters.xml.history.HistoryXml;
import ru.alex.testwork.controller.dto.HistoryDto;
import ru.alex.testwork.entity.History;

import java.time.LocalDate;

public class HistoryMapper {

	public static History xmlToEntity(HistoryXml xml) {
		History history = new History();
		history.setSecId(xml.getSecId());
		history.setTradeDate(LocalDate.parse(xml.getTradeDate()));
		history.setNumTrades(Double.parseDouble(xml.getNumTrades()));
		history.setOpen(Double.parseDouble(xml.getOpen()));
		history.setClose(Double.parseDouble(xml.getClose()));
		return history;
	}

	public static History dtoToEntity(HistoryDto dto) {
		History history = new History();
		history.setId(dto.getId());
		history.setSecId(dto.getSecId());
		history.setTradeDate(LocalDate.parse(dto.getTradeDate()));
		history.setNumTrades(Double.parseDouble(dto.getNumTrades()));
		history.setOpen(Double.parseDouble(dto.getOpen()));
		history.setClose(Double.parseDouble(dto.getClose()));
		return history;
	}

	public static HistoryDto entityToDto(History entity) {
		return new HistoryDto(entity.getId(),
				entity.getTradeDate().toString(),
				entity.getSecId(),
				String.valueOf(entity.getNumTrades()),
				String.valueOf(entity.getOpen()),
				String.valueOf(entity.getClose()));
	}
}
