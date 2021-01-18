package ru.alex.testwork.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/sql/mockmvc/drop_schema.sql",
		"/sql/mockmvc/create_schema.sql",
		"/sql/mockmvc/insert_schema.sql"})
class HistoryControllerMockmvcTest {
	static final String SECID1 = "ASSB";
	static final String SECID2 = "AQUA";
	static final String SECID3 = "MTLRP";

	static final String EXPECTED1 = "{\n" +
			"	 \"id\": 1,\n" +
			"    \"secId\": \"ASSB\",\n" +
			"    \"tradeDate\": \"2020-08-25\",\n" +
			"    \"numTrades\": \"81.0\",\n" +
			"    \"open\": \"0.8\",\n" +
			"    \"close\": \"0.785\"\n" +
			"}";

	static final String EXPECTED2 = "{\n" +
			"	 \"id\": 2,\n" +
			"    \"secId\": \"AQUA\",\n" +
			"    \"tradeDate\": \"2020-08-25\",\n" +
			"    \"numTrades\": \"968.0\",\n" +
			"    \"open\": \"221.5\",\n" +
			"    \"close\": \"220.0\"\n" +
			"}";

	static final String EXPECTED3 = "{\n" +
			"	 \"id\": 3,\n" +
			"    \"secId\": \"MTLRP\",\n" +
			"    \"tradeDate\": \"2020-08-25\",\n" +
			"    \"numTrades\": \"2523\",\n" +
			"    \"open\": \"73.65\",\n" +
			"    \"close\": \"73.3\"\n" +
			"}";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void findHistoryById_isOk() throws Exception {
		mockMvc.perform(get("/api/history/1"))
				.andExpect(status().isOk())
				.andExpect(content().json(EXPECTED1));
	}

	@Test
	void findHistoryById_isNotFound() throws Exception {
		String id = "10";
		String expected = "{\"status\":404,\"message\":\"History not found: ID=" + id + "\",\"httpStatus\":\"NOT_FOUND\"}";

		mockMvc.perform(get("/api/history/" + id))
				.andExpect(status().is(404))
				.andExpect(content().json(expected));
	}

	@Test
	void findHistoryBySecId_isOk() throws Exception {
		mockMvc.perform(get("/api/history/sec-id/" + SECID1))
				.andExpect(status().isOk())
				.andExpect(content().json(EXPECTED1));
	}

	@Test
	void findHistoryBySecId_isNotFound() throws Exception {
		String secId = "TEST";
		String expected = "{\"status\":404,\"message\":\"History not found: SECID=" + secId + "\",\"httpStatus\":\"NOT_FOUND\"}";
		mockMvc.perform(get("/api/history/sec-id/" + secId))
				.andExpect(status().is(404))
				.andExpect(content().json(expected));
	}


	@Test
	void createHistory_isCreated() throws Exception {
		final String newHistory = "{\n" +
//				"	 \"id\": 2,\n" +
				"    \"secId\": \"AQUA\",\n" +
				"    \"tradeDate\": \"2020-08-26\",\n" +
				"    \"numTrades\": \"969.0\",\n" +
				"    \"open\": \"222.5\",\n" +
				"    \"close\": \"221.0\"\n" +
				"}";

		mockMvc.perform(post("/api/history/"+SECID2).contentType(MediaType.APPLICATION_JSON).content(newHistory))
				.andExpect(status().isCreated())
				.andExpect(content().json(newHistory));
	}


}