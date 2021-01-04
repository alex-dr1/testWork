package ru.alex.testwork.services;

import ru.alex.testwork.domain.Securities;

public interface MoexService {
	Securities fetchSecuritiesBySecId(String secId);
}
