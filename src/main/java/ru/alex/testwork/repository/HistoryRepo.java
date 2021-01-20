package ru.alex.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.testwork.entity.History;

import java.util.List;

public interface HistoryRepo extends JpaRepository<History, Long> {
	List<History> findAllBySecId(String secId);
	boolean existsBySecId(String secId);
}
