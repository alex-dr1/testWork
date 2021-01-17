package ru.alex.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.testwork.entity.SecuritiesEntity;

import java.util.Optional;

public interface SecuritiesRepo extends JpaRepository<SecuritiesEntity, Long> {

	Optional<SecuritiesEntity> findSecuritiesBySecId(String secId);
}
