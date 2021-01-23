package ru.alex.testwork.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.alex.testwork.dto.ConsolidatedDto;
import ru.alex.testwork.entity.QHistory;
import ru.alex.testwork.entity.QSecurities;
import ru.alex.testwork.entity.Securities;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = {"/sql/mockmvc/drop_schema.sql",
		"/sql/mockmvc/create_schema.sql",
		"/sql/mockmvc/insert_schema.sql"})
class SecuritiesRepoTest {

	@Autowired
	private SecuritiesRepo securitiesRepo;

	@Autowired
	private EntityManager entityManager;

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
	void shouldFindSecuritiesBySecId() {
		String secId = "AQUA";
		Securities findSecurities = securitiesRepo.findSecuritiesBySecId(secId).get();
		assertThat(findSecurities.getSecId()).isEqualTo(secId);
	}

	@Test
	void showAllSecurities1() {
/*		BooleanExpression booleanExpression = QSecurities.securities.historySet.any().close.gt(2.0);
		List<Securities> all = Lists.newArrayList(securitiesRepo.findAll(booleanExpression));

		System.out.println("+-----------------------+");
		all
				.forEach(System.out::println);


//		securitiesRepo.findAll()
//				.forEach(System.out::println);*/
	}


	/*"""
    select p.id as p_id,
           p.title as p_title,
           pc.id as pc_id,
           pc.review as pc_review
    from PostComment pc
    join pc.post p
    order by pc.id
    """*/
	@Test
	void showDto(){
		String sql = """
				select s.secId,
				       h.numTrades
				from Securities s
				join History h
				""";

		List<ConsolidatedDto> consolidatedDtos = entityManager.createQuery(sql).getResultList();

		consolidatedDtos.forEach(System.out::println);
	}
}