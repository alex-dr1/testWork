package ru.alex.testwork.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = {"/sql/mockmvc/drop_schema.sql",
		"/sql/mockmvc/create_schema.sql",
		"/sql/mockmvc/insert_schema.sql"})
class ConsolidatedRepoTest {
	@Autowired
	ConsolidatedRepo consolidatedRepo;

	@Test
	void getAll(){
		consolidatedRepo.getAllConsolidated().forEach(System.out::println);
	}
}