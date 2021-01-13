package ru.alex.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.testwork.domain.entity.SecuritiesEntity;

public interface SecuritiesRepo extends JpaRepository<SecuritiesEntity, Long> {

	SecuritiesEntity findSecuritiesBySecId(String secId);
}
