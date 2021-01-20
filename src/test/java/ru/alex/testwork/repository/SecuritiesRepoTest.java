package ru.alex.testwork.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.alex.testwork.entity.Securities;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class SecuritiesRepoTest {

	@Autowired
	private SecuritiesRepo securitiesRepo;

	public static final String SEC_ID = "SSSS";
	public static final String REG_NUMBER = "reg number";
	public static final String NAME = "name";
	public static final String EMITENT_TITLE = "emitent title";
	Securities entity;

	@BeforeEach
	void setEntity() {
		entity = new Securities();
		entity.setEmitentTitle(EMITENT_TITLE);
		entity.setName(NAME);
		entity.setRegNumber(REG_NUMBER);
		entity.setSecId(SEC_ID);
		entity.setId(null);
		entity.setHistorySet(null);
	}
	@Test
	void shouldSaveSecurities() {
		Securities savedSecurities = securitiesRepo.save(entity);

		assertThat(savedSecurities)
				.usingRecursiveComparison()
				.isEqualTo(entity);
	}

	@Test
	@Sql("classpath:sql/securities.sql")
	void shouldFindSecuritiesBySecId() {
		String secId = "AQUA";
		Securities findSecurities = securitiesRepo.findSecuritiesBySecId(secId).get();
		assertThat(findSecurities.getSecId()).isEqualTo(secId);
	}


}