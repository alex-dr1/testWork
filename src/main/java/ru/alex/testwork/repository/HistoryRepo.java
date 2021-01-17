package ru.alex.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.testwork.entity.HistoryEntity;

import java.util.Optional;

public interface HistoryRepo extends JpaRepository<HistoryEntity, Long> {
	Optional<HistoryEntity> findHistoryBySecId(String secId);
}
