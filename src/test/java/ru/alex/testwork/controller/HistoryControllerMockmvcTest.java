package ru.alex.testwork.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoryControllerMockmvcTest extends BaseIntegrationTest {
	static final String SECID = "ASSB";

	static final String EXPECTED = "{\n" +
			"	 \"id\": 1,\n" +
			"    \"secId\": \"ASSB\",\n" +
			"    \"tradeDate\": \"2020-08-25\",\n" +
			"    \"numTrades\": \"81.0\",\n" +
			"    \"open\": \"0.8\",\n" +
			"    \"close\": \"1.1\"\n" +
			"}";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void findHistoryById_isOk() throws Exception {
		mockMvc.perform(get("/api/history/1"))
				.andExpect(status().isOk())
				.andExpect(content().json(EXPECTED));
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
		String expected = "[" + EXPECTED + "]";
		mockMvc.perform(get("/api/history/sec-id/" + SECID))
				.andExpect(status().isOk())
				.andExpect(content().json(expected));
	}

	@Test
	void findHistoryBySecId_isNotFound() throws Exception {
		String secId = "TEST";
		String expected = "[]";
		mockMvc.perform(get("/api/history/sec-id/" + secId))
				.andExpect(status().isOk())
				.andExpect(content().json(expected));
	}


	@Test
	void createHistory_isCreated() throws Exception {
		final String newHistory = "{\n" +
				"    \"secId\": \"AQUA\",\n" +
				"    \"tradeDate\": \"2020-08-26\",\n" +
				"    \"numTrades\": \"969.0\",\n" +
				"    \"open\": \"222.5\",\n" +
				"    \"close\": \"221.0\"\n" +
				"}";

		mockMvc.perform(post("/api/history").contentType(MediaType.APPLICATION_JSON).content(newHistory))
				.andExpect(status().isCreated())
				.andExpect(content().json(newHistory));
	}

	@Test
		// secId == null
	void createHistory_isBadRequest0() throws Exception {
		final String newHistory = "{\n" +
				"    \"tradeDate\": \"2020-08-25\",\n" +
				"    \"numTrades\": \"968.0\",\n" +
				"    \"open\": \"221.5\",\n" +
				"    \"close\": \"220.0\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Create error: secId == null\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(post("/api/history").contentType(MediaType.APPLICATION_JSON).content(newHistory))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected));
	}

	@Test
		// id != null
	void createHistory_isBadRequest1() throws Exception {
		final String newHistory = "{\n" +
				"	 \"id\": 2,\n" +
				"    \"secId\": \"AQUA\",\n" +
				"    \"tradeDate\": \"2020-08-25\",\n" +
				"    \"numTrades\": \"968.0\",\n" +
				"    \"open\": \"221.5\",\n" +
				"    \"close\": \"220.0\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Create error: id != null\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(post("/api/history").contentType(MediaType.APPLICATION_JSON).content(newHistory))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected));
	}

	@Test
		// secId not exists
	void createHistory_isBadRequest2() throws Exception {
		final String newHistory = "{\n" +
//				"	 \"id\": 2,\n" +
				"    \"secId\": \"AQUA2\",\n" +
				"    \"tradeDate\": \"2020-08-25\",\n" +
				"    \"numTrades\": \"968.0\",\n" +
				"    \"open\": \"221.5\",\n" +
				"    \"close\": \"220.0\"\n" +
				"}";

		final String expected = "{\"status\":404,\"message\":\"Securities not found: SECID=AQUA2\",\"httpStatus\":\"NOT_FOUND\"}";

		mockMvc.perform(post("/api/history").contentType(MediaType.APPLICATION_JSON).content(newHistory))
				.andExpect(status().is(404))
				.andExpect(content().json(expected));
	}

	@Test
	void updateHistory_isOk() throws Exception {
		final String newHistory = "{\n" +
				"\"id\": 3,\n" +
				"\"secId\": \"MTLRP\",\n" +
				"\"tradeDate\": \"2020-08-25\",\n" +
				"\"numTrades\": \"2524.0\",\n" +
				"\"open\": \"73.66\",\n" +
				"\"close\": \"73.4\"\n" +
				"}";

		mockMvc.perform(put("/api/history").contentType(MediaType.APPLICATION_JSON).content(newHistory))
				.andExpect(status().isOk())
				.andExpect(content().json(newHistory));
	}

	@Test
		// id == null
	void updateHistory_isBadRequest0() throws Exception {
		final String newHistory = "{\n" +
//				"\"id\": 3,\n" +
				"\"secId\": \"MTLRP\",\n" +
				"\"tradeDate\": \"2020-08-25\",\n" +
				"\"numTrades\": \"2524\",\n" +
				"\"open\": \"73.66\",\n" +
				"\"close\": \"73.4\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Update error: id == null\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(put("/api/history").contentType(MediaType.APPLICATION_JSON).content(newHistory))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected));
	}

	@Test
		// secId == null
	void updateHistory_isBadRequest1() throws Exception {
		final String newHistory = "{\n" +
				"\"id\": 3,\n" +
//				"\"secId\": \"MTLRP\",\n" +
				"\"tradeDate\": \"2020-08-25\",\n" +
				"\"numTrades\": \"2524\",\n" +
				"\"open\": \"73.66\",\n" +
				"\"close\": \"73.4\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Update error: secId == null\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(put("/api/history").contentType(MediaType.APPLICATION_JSON).content(newHistory))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected));
	}

	@Test
		// secId not exists
	void updateHistory_isBadRequest3() throws Exception {
		final String newHistory = "{\n" +
				"\"id\": 3,\n" +
				"\"secId\": \"MTLRP2\",\n" +
				"\"tradeDate\": \"2020-08-25\",\n" +
				"\"numTrades\": \"2524\",\n" +
				"\"open\": \"73.66\",\n" +
				"\"close\": \"73.4\"\n" +
				"}";

		final String expected = "{\"status\":404,\"message\":\"Securities not found: SECID=MTLRP2\",\"httpStatus\":\"NOT_FOUND\"}";

		mockMvc.perform(put("/api/history").contentType(MediaType.APPLICATION_JSON).content(newHistory))
				.andExpect(status().is(404))
				.andExpect(content().json(expected));
	}

	@Test
	void deleteHistory_isOK() throws Exception {
		mockMvc.perform(delete("/api/history/4"))
				.andExpect(status().isOk())
		;
	}

	@Test
	void deleteHistory_isNotFound() throws Exception {
		mockMvc.perform(delete("/api/history/10"))
				.andExpect(status().is(404))
		;
	}
}