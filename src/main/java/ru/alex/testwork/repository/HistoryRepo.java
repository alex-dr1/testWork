package ru.alex.testwork.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alex.testwork.domain.entity.HistoryEntity;

public interface HistoryRepo extends CrudRepository<HistoryEntity, Long> {
}
