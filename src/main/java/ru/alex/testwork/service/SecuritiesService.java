package ru.alex.testwork.service;

import ru.alex.testwork.controller.dto.SecuritiesDto;

import java.util.List;

public interface SecuritiesService {
	SecuritiesDto findOneById(Long id);

	SecuritiesDto save(SecuritiesDto model);

	SecuritiesDto update(SecuritiesDto model);

	Long delete(Long id);

	List<SecuritiesDto> getAllSecurities();

	SecuritiesDto findOneBySecId(String secId);
}
