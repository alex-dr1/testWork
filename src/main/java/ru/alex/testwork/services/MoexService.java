package ru.alex.testwork.services;

import ru.alex.testwork.domain.entity.SecuritiesEntity;

public interface MoexService {
	SecuritiesEntity fetchSecuritiesBySecId(String secId);
}
