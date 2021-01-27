package ru.alex.testwork.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/sql/integration/drop_schema.sql",
		"/sql/integration/create_schema.sql",
		"/sql/integration/insert_schema.sql"})
public class BaseIntegrationTest {
}
