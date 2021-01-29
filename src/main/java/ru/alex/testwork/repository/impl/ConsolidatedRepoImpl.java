package ru.alex.testwork.repository.impl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.alex.testwork.controller.dto.ConsolidatedDto;
import ru.alex.testwork.entity.QHistory;
import ru.alex.testwork.entity.QSecurities;
import ru.alex.testwork.repository.ConsolidatedRepo;
import ru.alex.testwork.repository.QPredicate;
import ru.alex.testwork.service.DirSort;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
@Slf4j
public class ConsolidatedRepoImpl implements ConsolidatedRepo {

	final EntityManager entityManager;
	final QSecurities securities = QSecurities.securities;
	final QHistory history = QHistory.history;
	final Map<String, OrderSpecifier<?>> ORDER_SPECIFIER_MAP = initOrderSpecifierMap();

	@Override
	public List<ConsolidatedDto> findAllConsolidated(Optional<String> emitentTitle,
													 Optional<LocalDate> tradeDate,
													 List<DirSort> dirSortList) {
		final JPAQuery<Void> query = new JPAQuery<>(entityManager);
		final Predicate predicates = generatedPredicate(emitentTitle, tradeDate);
		final OrderSpecifier<?>[] orderSpecifiers = generatedOrderSpecifiers(dirSortList);

		return query
				.select(Projections.constructor(ConsolidatedDto.class,
						securities.secId, securities.regNumber, securities.name, securities.emitentTitle,
						history.tradeDate, history.numTrades, history.open, history.close))
				.from(securities).join(history).on(securities.secId.eq(history.secId))
				.where(predicates)
				.orderBy(orderSpecifiers)
				.fetch();
	}

	private OrderSpecifier<?>[] generatedOrderSpecifiers(List<DirSort> dirSortList) {
		if (dirSortList.isEmpty()) return new OrderSpecifier[0];

		List<OrderSpecifier<?>> orderSpecifierList = dirSortList.stream()
				.map(dirSort -> dirSort.getName() + dirSort.getDirection())
				.map(ORDER_SPECIFIER_MAP::get)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

		return orderSpecifierList.toArray(OrderSpecifier<?>[]::new);
	}

	private Predicate generatedPredicate(Optional<String> emitentTitle, Optional<LocalDate> tradeDate) {
		return QPredicate.builder()
				.add(emitentTitle, securities.emitentTitle::like)
				.add(tradeDate, history.tradeDate::eq)
				.buildAnd();
	}

	private Map<String, OrderSpecifier<?>> initOrderSpecifierMap() {
		Map<String, OrderSpecifier<?>> result = new HashMap<>();

		result.put("secIdASC", securities.secId.asc());
		result.put("secIdDESC", securities.secId.desc());
		result.put("regNumberASC", securities.regNumber.asc());
		result.put("regNumberDESC", securities.regNumber.desc());
		result.put("nameASC", securities.name.asc());
		result.put("nameDESC", securities.name.desc());
		result.put("emitentTitleASC", securities.emitentTitle.asc());
		result.put("emitentTitleDESC", securities.emitentTitle.desc());

		result.put("tradeDateASC", history.tradeDate.asc());
		result.put("tradeDateDESC", history.tradeDate.desc());
		result.put("numTradesASC", history.numTrades.asc());
		result.put("numTradesDESC", history.numTrades.desc());
		result.put("openASC", history.open.asc());
		result.put("openDESC", history.open.desc());
		result.put("closeASC", history.close.asc());
		result.put("closeDESC", history.close.desc());

		return result;
	}
}
