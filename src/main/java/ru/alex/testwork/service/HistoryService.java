package ru.alex.testwork.service;

import ru.alex.testwork.dto.HistoryDto;

public interface HistoryService {

	HistoryDto findHistoryById(Long id);

	HistoryDto findHistoryBySecId(String secId);

	HistoryDto create(HistoryDto dto);

	HistoryDto update(HistoryDto dto);

	Long delete(Long id);
}
