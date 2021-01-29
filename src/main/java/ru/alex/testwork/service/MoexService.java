package ru.alex.testwork.service;

import ru.alex.testwork.entity.Securities;

import java.util.Optional;

public interface MoexService {
	Optional<Securities> fetchSecuritiesBySecId(String secId);
}
