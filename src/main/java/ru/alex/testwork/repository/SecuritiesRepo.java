package ru.alex.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.alex.testwork.entity.Securities;

import java.util.Optional;

public interface SecuritiesRepo	extends JpaRepository<Securities, Long> {

	Optional<Securities> findSecuritiesBySecId(String secId);

	boolean existsBySecId(String secId);

}

