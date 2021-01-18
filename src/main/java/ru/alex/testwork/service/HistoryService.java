package ru.alex.testwork.service;

import ru.alex.testwork.dto.HistoryDto;

public interface HistoryService {

	HistoryDto findHistoryById(Long id);

	HistoryDto findHistoryBySecId(String secId);

	HistoryDto save(HistoryDto dto);
}
