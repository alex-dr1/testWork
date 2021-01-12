package ru.alex.testwork.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alex.testwork.domain.entity.SecuritiesEntity;

public interface SecuritiesRepo extends CrudRepository<SecuritiesEntity, Long> {

	SecuritiesEntity findSecuritiesBySecId(String secId);
}
