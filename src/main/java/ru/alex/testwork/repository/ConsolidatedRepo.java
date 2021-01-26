package ru.alex.testwork.repository;

import ru.alex.testwork.controller.dto.ConsolidatedDto;
import ru.alex.testwork.service.DirSort;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ConsolidatedRepo {
	List<ConsolidatedDto> findAllConsolidated(Optional<String> emitentTitle,
											  Optional<Date> tradeDate,
											  List<DirSort> dirSortList);
}
