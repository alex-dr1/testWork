package ru.alex.testwork.service;

import ru.alex.testwork.entity.SecuritiesEntity;

public interface MoexService {
	SecuritiesEntity fetchSecuritiesBySecId(String secId);
}
