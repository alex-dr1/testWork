package ru.alex.testwork.service;

import ru.alex.testwork.domain.entity.SecuritiesEntity;

public interface MoexService {
	SecuritiesEntity fetchSecuritiesBySecId(String secId);
}
