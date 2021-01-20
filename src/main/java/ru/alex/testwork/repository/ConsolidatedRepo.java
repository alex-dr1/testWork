package ru.alex.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alex.testwork.dto.ConsolidatedDto;
import ru.alex.testwork.entity.Securities;

import java.util.List;

public interface ConsolidatedRepo extends JpaRepository<Securities, Long> {
	@Query("SELECT new ru.alex.testwork.dto.ConsolidatedDto(s.secId, s.regNumber, s.name, s.emitentTitle, h.tradeDate, h.numTrades, h.open, h.close) " +
			"FROM Securities s " +
			"JOIN s.historySet h")
	List<ConsolidatedDto> getAllConsolidated();
}
