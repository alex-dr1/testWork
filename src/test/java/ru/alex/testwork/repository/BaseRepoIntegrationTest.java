package ru.alex.testwork.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = {"/sql/integration/drop_schema.sql",
		"/sql/integration/create_schema.sql",
		"/sql/integration/insert_schema.sql"})
public class BaseRepoIntegrationTest {
}
