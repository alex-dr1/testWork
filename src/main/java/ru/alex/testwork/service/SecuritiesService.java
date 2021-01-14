package ru.alex.testwork.service;

import ru.alex.testwork.domain.model.Securities;

public interface SecuritiesService {
	public Securities findOneById(Long id);

	public Securities save(Securities model);

	public Securities update(Securities model);

	public Long delete(Long id);
}
