package ru.alex.testwork.repository.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.alex.testwork.dto.ConsRequest;
import ru.alex.testwork.dto.ConsolidatedDto;
import ru.alex.testwork.entity.History;
import ru.alex.testwork.entity.History_;
import ru.alex.testwork.entity.Securities;
import ru.alex.testwork.entity.Securities_;
import ru.alex.testwork.service.DirSort;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class ConsolidatedRepoImpl {
	private static final String DATA_PATTERN = "yyyy-MM-dd";

	final EntityManager em;

	public void getConsolidatedTable(ConsRequest consRequest) throws ParseException {


		//Create query
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ConsolidatedDto> cq = cb.createQuery(ConsolidatedDto.class);

		//Define FROM
		final Root<Securities> tSecurities = cq.from(Securities.class);
		final Join<Securities, History> tHistory = tSecurities.join(Securities_.historySet);

		//Define DTO projection
		cq.select(cb.construct(
				ConsolidatedDto.class,
				tSecurities.get(Securities_.SEC_ID),
				tSecurities.get(Securities_.REG_NUMBER),
				tSecurities.get(Securities_.NAME),
				tSecurities.get(Securities_.EMITENT_TITLE),
				tHistory.get(History_.TRADE_DATE),
				tHistory.get(History_.NUM_TRADES),
				tHistory.get(History_.OPEN),
				tHistory.get(History_.CLOSE)
		));

		//Define WHERE
		final ParameterExpression<String> parameterEmitentTitle = cb.parameter(String.class, Securities_.EMITENT_TITLE);
		final ParameterExpression<Date> parameterTradeDate = cb.parameter(Date.class, History_.TRADE_DATE);

		final Predicate likeEmitentTitle = cb.like(tSecurities.get(Securities_.EMITENT_TITLE), parameterEmitentTitle);
		final Predicate equalTradeDate = cb.equal(tHistory.<Date>get(History_.TRADE_DATE), parameterTradeDate);
		Predicate[] predicates = {null, likeEmitentTitle};
		cq.where(predicates);
/*
		//Define ORDER BY
		cq.orderBy(List.of(cb.desc(tSecurities.get(Phone_.id)), cb.asc(tSecurities.get(Phone_.number))));
*/
		//Execute query
		final String filterEmitentTitle = "%" + consRequest.getFilterEmitentTitle().trim() + "%";
		final String filterTradeDate = consRequest.getFilterTradeDate().trim();

		TypedQuery<ConsolidatedDto> typedQuery = em.createQuery(cq);
		typedQuery.setParameter(Securities_.EMITENT_TITLE, filterEmitentTitle);
		//TODO Exception
		typedQuery.setParameter(History_.TRADE_DATE, Date.valueOf(filterTradeDate));
		typedQuery.getResultList().forEach(System.out::println);
	}

	public void findConsolidatedByParameters(String emitentTitle, Optional<Date> tradeDate, Optional<List<DirSort>> directionSortMap) {
		log.warn(emitentTitle);
		tradeDate.ifPresent((date)->log.warn(date.toString()));
		directionSortMap.ifPresent(list->log.warn(list.toString()));
	}
}
