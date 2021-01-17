package ru.alex.testwork.mapper;

import ru.alex.testwork.dto.HistoryDto;
import ru.alex.testwork.entity.HistoryEntity;
import ru.alex.testwork.xml.history.HistoryXml;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class HistoryMapper {

	private static final String DATA_PATTERN = "yyyy-MM-dd";

	public static HistoryEntity xmlToEntity(HistoryXml xml){
		HistoryEntity history = new HistoryEntity();
		history.setSecId(xml.getSecId());
		history.setTradeDate(Date.valueOf(xml.getTradeDate()));
		history.setNumTrades(Double.parseDouble(xml.getNumTrades()));
		history.setOpen(Double.parseDouble(xml.getOpen()));
		history.setClose(Double.parseDouble(xml.getClose()));
		return history;
	}

	public static HistoryEntity dtoToEntity(HistoryDto dto){
		HistoryEntity history = new HistoryEntity();
		history.setSecId(dto.getSecId());
		history.setTradeDate(Date.valueOf(dto.getTradeDate()));
		history.setNumTrades(Double.parseDouble(dto.getNumTrades()));
		history.setOpen(Double.parseDouble(dto.getOpen()));
		history.setClose(Double.parseDouble(dto.getClose()));
		return history;
	}

	public static HistoryDto entityToDto(HistoryEntity entity) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATA_PATTERN);

		return new HistoryDto(entity.getId(),
				simpleDateFormat.format(entity.getTradeDate()),
				entity.getSecId(),
				String.valueOf(entity.getNumTrades()),
				String.valueOf(entity.getOpen()),
				String.valueOf(entity.getClose()));
	}
}
