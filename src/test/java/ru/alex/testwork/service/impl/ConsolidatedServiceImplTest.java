package ru.alex.testwork.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.alex.testwork.controller.dto.ConsolidatedRequest;

import java.text.ParseException;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"/sql/mockmvc/drop_schema.sql",
		"/sql/mockmvc/create_schema.sql",
		"/sql/mockmvc/insert_schema.sql"})
class ConsolidatedServiceImplTest {

	@Autowired
	ConsolidatedServiceImpl consolidatedService;

	ConsolidatedRequest consRequest;

	@BeforeEach
	void setUp() {
		/*Map<String, Sort.Direction> sortMap = new HashMap<>();

		sortMap.put("name1", Sort.Direction.ASC);
		sortMap.put("name2", Sort.Direction.DESC);
		sortMap.put("name3", Sort.Direction.ASC);

		consRequest = ConsRequest.builder()
				.filterEmitentTitle("е")
//				.filterTradeDate("")// 2020-08-25
				.sortField(sortMap)
				.build();*/
	}

	@Test
	void getConsolidatedTable() throws ParseException {
		/*final List<ConsolidatedDto> consolidatedTable = *///consolidatedService.getConsolidatedTable(consRequest);
//		consolidatedTable.forEach(System.out::println);
	}

}