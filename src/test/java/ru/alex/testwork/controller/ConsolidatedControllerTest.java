package ru.alex.testwork.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/sql/mockmvc/drop_schema.sql",
		"/sql/mockmvc/create_schema.sql",
		"/sql/mockmvc/insert_schema.sql"})
class ConsolidatedControllerTest {


	@Autowired
	private MockMvc mockMvc;

	@Test // filterTradeDate is bad
	void getConsolidated_isOk() throws Exception {
		final String bodyRequest = "{\n" +
				"    \"filterEmitentTitle\": \"ечел\",\n" +
				"    \"filterTradeDate\": \"2020-08-25\",\n" +
				"    \"sortField\": {\n" +
				"        \"open\": \"asc\",\n" +
				"        \"numTrades\": \"desc\"\n" +
				"    }\n" +
				"}";

		mockMvc.perform(get("/api/consolidated").contentType(MediaType.APPLICATION_JSON).content(bodyRequest))
				.andExpect(status().isOk())

		;
	}

	@Test // filterTradeDate is bad
	void getConsolidated_isBadRequest0() throws Exception {
		final String bodyRequest = "{\n" +
				"    \"filterEmitentTitle\": \"emww\",\n" +
				"    \"filterTradeDate\": \"2021-01-0d\",\n" +
				"    \"sortField\": {\n" +
				"        \"name3\": \"asc\",\n" +
				"        \"name2\": \"desc\",\n" +
				"        \"name1\": \"asc\",\n" +
				"        \"name4\": \"asc\"\n" +
				"    }\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Expected date in format[yyyy-MM-dd]\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(get("/api/consolidated").contentType(MediaType.APPLICATION_JSON).content(bodyRequest))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;
	}

	@Test // sortField is bad
	void getConsolidated_isBadRequest1() throws Exception {
		final String bodyRequest = "{\n" +
				"    \"filterEmitentTitle\": \"emww\",\n" +
				"    \"filterTradeDate\": \"2021-01-01\",\n" +
				"    \"sortField\": {\n" +
				"        \"name3\": \"-sc\",\n" +
				"        \"name2\": \"desc\",\n" +
				"        \"name1\": \"asc\",\n" +
				"        \"name4\": \"asc\"\n" +
				"    }\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Invalid value '-sc' for orders given! Has to be either 'desc' or 'asc' (case insensitive).\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(get("/api/consolidated").contentType(MediaType.APPLICATION_JSON).content(bodyRequest))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;
	}
}