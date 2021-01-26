package ru.alex.testwork.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.alex.testwork.dto.ConsRequest;
import ru.alex.testwork.dto.ConsolidatedDto;
import ru.alex.testwork.exception.BadRestRequestException;
import ru.alex.testwork.repository.impl.ConsolidatedRepoImpl;
import ru.alex.testwork.service.DirSort;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ConsolidatedServiceImpl {

	final ConsolidatedRepoImpl repo;

	public List<ConsolidatedDto> getConsolidatedTable(ConsRequest consRequest) throws ParseException {

		repo.getConsolidatedTable(consRequest);

		/*final String emitentTitle = generatedEmitentTitle(consRequest.getFilterEmitentTitle());
		final Optional<Date> tradeDate = generatedTradeDate(consRequest.getFilterTradeDate());
		final Optional<List<DirSort>> dirSorts = generatedSortList(consRequest.getSortField());

		repo.findConsolidatedByParameters(emitentTitle, tradeDate, dirSorts);
*/
		return null;
	}

	// Map to List<DirSort>
	private Optional<List<DirSort>> generatedSortList(Map<String, String> sortField) {
		if (sortField == null) return Optional.empty();
		try {
			return Optional.of(sortField.entrySet().stream()
					.map(entry -> new DirSort(entry.getKey(), Sort.Direction.fromString(entry.getValue())))
					.collect(Collectors.toList()));

		} catch (IllegalArgumentException ex) {
			throw new BadRestRequestException(ex.getMessage());
		}

	}

	// TODO Delete method
	private Optional<Map<String, Sort.Direction>> generatedSortMap(Map<String, String> sortField) {
		if (sortField == null) return Optional.empty();
		try {
			return Optional.of(sortField.entrySet()
					.stream()
					.collect(Collectors.toMap(
							Map.Entry::getKey,
							direct -> Sort.Direction.fromString(direct.getValue())
					)));

		} catch (IllegalArgumentException ex) {
			throw new BadRestRequestException(ex.getMessage());
		}
	}

	private Optional<Date> generatedTradeDate(String filterTradeDate) {
		if (filterTradeDate == null) return Optional.empty();
		try {
			return Optional.of(Date.valueOf(filterTradeDate));
		} catch (IllegalArgumentException ex) {
			throw new BadRestRequestException("Expected date in format[yyyy-MM-dd]");
		}
	}

	private String generatedEmitentTitle(String filterEmitentTitle) {
		String result;
		if (filterEmitentTitle == null) {
			return "%%";
		} else {
			return "%" + filterEmitentTitle.trim() + "%";
		}
	}
}

