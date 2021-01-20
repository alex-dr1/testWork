package ru.alex.testwork.service;

import ru.alex.testwork.entity.Securities;

public interface MoexService {
	Securities fetchSecuritiesBySecId(String secId);
}
