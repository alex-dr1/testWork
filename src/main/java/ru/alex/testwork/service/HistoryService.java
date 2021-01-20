package ru.alex.testwork.service;

import ru.alex.testwork.dto.HistoryDto;

import java.util.List;

public interface HistoryService {

	HistoryDto findHistoryById(Long id);

	List<HistoryDto> findAllHistoryBySecId(String secId);

	HistoryDto create(HistoryDto dto);

	HistoryDto update(HistoryDto dto);

	Long delete(Long id);
}
