package ru.alex.testwork.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.alex.testwork.controller.dto.ConsolidatedRequest;
import ru.alex.testwork.controller.dto.ConsolidatedDto;
import ru.alex.testwork.exception.BadRestRequestException;
import ru.alex.testwork.repository.ConsolidatedRepo;
import ru.alex.testwork.service.ConsolidatedService;
import ru.alex.testwork.service.DirSort;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsolidatedServiceImpl implements ConsolidatedService {

	final ConsolidatedRepo consolidatedRepo;

	@Override
	public List<ConsolidatedDto> getAllConsolidated(ConsolidatedRequest consRequest) {

		final Optional<String> emitentTitle = generatedEmitentTitle(consRequest.getFilterEmitentTitle());
		final Optional<LocalDate> tradeDate = generatedTradeDate(consRequest.getFilterTradeDate());
		final List<DirSort> dirSorts = generatedSortList(consRequest.getSortField());

		return Collections.unmodifiableList(consolidatedRepo.findAllConsolidated(emitentTitle, tradeDate, dirSorts));
	}

	// Map to List<DirSort>
	private List<DirSort> generatedSortList(Map<String, String> sortField) {
		if (sortField == null) return new ArrayList<>();
		try {
			return sortField.entrySet().stream()
					.map(entry -> new DirSort(entry.getKey(), Sort.Direction.fromString(entry.getValue())))
					.collect(Collectors.toList());

		} catch (IllegalArgumentException ex) {
			throw new BadRestRequestException(ex.getMessage());
		}

	}

	private Optional<LocalDate> generatedTradeDate(String filterTradeDate) {
		if (filterTradeDate == null) return Optional.empty();
		try {
			return Optional.of(LocalDate.parse(filterTradeDate));
		} catch (DateTimeParseException ex) {
			throw new BadRestRequestException("Expected date in format[yyyy-MM-dd]");
		}
	}

	private Optional<String> generatedEmitentTitle(String filterEmitentTitle) {
		if (filterEmitentTitle == null) {
			return Optional.empty();
		} else {
			return Optional.of("%" + filterEmitentTitle.trim() + "%");
		}
	}
}

