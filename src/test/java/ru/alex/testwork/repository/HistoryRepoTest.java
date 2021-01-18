package ru.alex.testwork.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.alex.testwork.entity.HistoryEntity;
import ru.alex.testwork.entity.SecuritiesEntity;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class HistoryRepoTest {

	@Autowired
	HistoryRepo historyRepo;

	@Autowired
	SecuritiesRepo securitiesRepo;

	public static final String SEC_ID = "AQUA";
	public static final Date TRADE_DATE = Date.valueOf("2021-01-14");
	public static final double NUM_TRADES = 2345.8;
	public static final double OPEN = 10.5;
	public static final double CLOSE = 9.3;

	HistoryEntity historyEntity;

	@BeforeEach
	void setEntity() {
		historyEntity = new HistoryEntity();
		historyEntity.setSecId(SEC_ID);
		historyEntity.setTradeDate(TRADE_DATE);
		historyEntity.setNumTrades(NUM_TRADES);
		historyEntity.setOpen(OPEN);
		historyEntity.setClose(CLOSE);
	}

	@Test
	@Sql("classpath:sql/securities.sql")
	void shouldSaveAndRetrieveHistory() {
		SecuritiesEntity securitiesEntity = securitiesRepo.findSecuritiesBySecId(SEC_ID).get();
		historyEntity.setSecurities(securitiesEntity);
		HistoryEntity savedHistory = historyRepo.save(historyEntity);

		assertThat(savedHistory)
				.usingRecursiveComparison()
				.isEqualTo(historyEntity);

		HistoryEntity findHistory = historyRepo.findBySecId(SEC_ID).get();

		assertThat(findHistory)
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(savedHistory);
	}


}