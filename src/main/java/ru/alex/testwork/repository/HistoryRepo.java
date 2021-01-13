package ru.alex.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.testwork.domain.entity.HistoryEntity;

public interface HistoryRepo extends JpaRepository<HistoryEntity, Long> {
}
