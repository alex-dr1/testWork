package ru.alex.testwork.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.alex.testwork.entity.History;
import ru.alex.testwork.entity.Securities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HistoryRepoTest extends BaseRepoIntegrationTest {

	@Autowired
	HistoryRepo historyRepo;

	@Autowired
	SecuritiesRepo securitiesRepo;

	public static final String SEC_ID = "AQUA";
	public static final LocalDate TRADE_DATE = LocalDate.parse("2021-01-14");
	public static final double NUM_TRADES = 2345.8;
	public static final double OPEN = 10.5;
	public static final double CLOSE = 9.3;

	History historyEntity;

	@BeforeEach
	void setEntity() {
		historyEntity = new History();
		historyEntity.setSecId(SEC_ID);
		historyEntity.setTradeDate(TRADE_DATE);
		historyEntity.setNumTrades(NUM_TRADES);
		historyEntity.setOpen(OPEN);
		historyEntity.setClose(CLOSE);
	}

	@Test
	void shouldSaveAndRetrieveHistory() {
		Securities securitiesEntity = securitiesRepo.findSecuritiesBySecId(SEC_ID).get();
		historyEntity.setSecurities(securitiesEntity);
		History savedHistory = historyRepo.save(historyEntity);

		assertThat(savedHistory)
				.usingRecursiveComparison()
				.isEqualTo(historyEntity);

		List<History> allHistory = historyRepo.findAllBySecId(SEC_ID);

		assertThat(allHistory.get(1))
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(savedHistory);
	}


}
