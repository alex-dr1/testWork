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
class SecuritiesControllerMockmvcTest {

	static final String EXPECTED1 = "{\n" +
			"    \"id\": 1,\n" +
			"    \"secId\": \"ASSB\",\n" +
			"    \"regNumber\": \"1-01-55064-E\",\n" +
			"    \"name\": \"\\\"Астраханская ЭСК\\\" ПАО\",\n" +
			"    \"emitentTitle\": \"Публичное акционерное общество \\\"Астраханская энергосбытовая компания\\\"\"\n" +
			"}";

	static final String EXPECTED2 = "{\n" +
			"    \"id\": 2,\n" +
			"    \"secId\": \"AQUA\",\n" +
			"    \"regNumber\": \"1-01-04461-D\",\n" +
			"    \"name\": \"Русская Аквакультура ПАО ао\",\n" +
			"    \"emitentTitle\": \"Публичное  акционерное общество \\\"Русская Аквакультура\\\"\"\n" +
			"}";
	static final String EXPECTED3 = "{\n" +
			"	 \"id\": 3,\n" +
			"    \"secId\": \"MTLRP\",\n" +
			"    \"regNumber\": \"2-01-55005-E\",\n" +
			"    \"name\": \"Мечел ПАО ап\",\n" +
			"    \"emitentTitle\": \"Публичное акционерное общество \\\"Мечел\\\"\"\n" +
			"}";


	@Autowired
	private MockMvc mockMvc;

	@Test
	void getOneById_isOK() throws Exception {
		mockMvc.perform(get("/api/securities/2"))
				.andExpect(status().isOk())
				.andExpect(content().json(EXPECTED2))
		;
	}

	@Test
	void getOneById_isNotFound() throws Exception {
		mockMvc.perform(get("/api/securities/5"))
				.andExpect(status().is(404))
		;
	}

	@Test
	void getOneBySecId_isOk() throws Exception {
		mockMvc.perform(get("/api/securities/sec-id/ASSB"))
				.andExpect(status().isOk())
				.andExpect(content().json(EXPECTED1))
		;
	}

	@Test
	void getOneBySecId_isNotFound() throws Exception {
		mockMvc.perform(get("/api/securities/sec-id/TEST"))
				.andExpect(status().is(404))
		;
	}

	@Test
	void createSecurities_isCreated() throws Exception {
		final String newSecurities = "{\n" +
				"    \"secId\": \"TEST1\",\n" +
				"    \"regNumber\": \"2-test-1\",\n" +
				"    \"name\": \"Русский 1\",\n" +
				"    \"emitentTitle\": \"test1test1test1test1test1\"\n" +
				"}";

		mockMvc.perform(post("/api/securities").contentType(MediaType.APPLICATION_JSON).content(newSecurities))
				.andExpect(status().isCreated())
				.andExpect(content().json(newSecurities))
		;
	}

	@Test
		// id is exist
	void createSecurities_isBadRequest0() throws Exception {
		final String newSecurities = "{\n" +
				"	 \"id\": 1,\n" +
				"    \"secId\": \"TEST1\",\n" +
				"    \"regNumber\": \"2-test-1\",\n" +
				"    \"name\": \"Русский 1\",\n" +
				"    \"emitentTitle\": \"test1test1test1test1test1\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Create error: id is exist\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(post("/api/securities").contentType(MediaType.APPLICATION_JSON).content(newSecurities))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;
	}


	@Test
		// secId is exist
	void createSecurities_isBadRequest1() throws Exception {
		final String newSecurities = "{\n" +
				"    \"secId\": \"AQUA\",\n" +
				"    \"regNumber\": \"2-test-1\",\n" +
				"    \"name\": \"Русский 1\",\n" +
				"    \"emitentTitle\": \"test1test1test1test1test1\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Create error: secId is exist\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(post("/api/securities").contentType(MediaType.APPLICATION_JSON).content(newSecurities))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;
	}

	@Test
		// secId == null
	void createSecurities_isBadRequest2() throws Exception {
		final String newSecurities = "{\n" +
				"    \"regNumber\": \"2-test-1\",\n" +
				"    \"name\": \"Русский 1\",\n" +
				"    \"emitentTitle\": \"test1test1test1test1test1\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Create error: secId == null\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(post("/api/securities").contentType(MediaType.APPLICATION_JSON).content(newSecurities))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;
	}

	@Test
		// name = blank
	void createSecurities_isBadRequest3() throws Exception {
		final String newSecurities = "{\n" +
				"    \"secId\": \"TEST2\",\n" +
				"    \"regNumber\": \"2-test-2\",\n" +
				"    \"name\": \"\",\n" +
				"    \"emitentTitle\": \"test2\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"name not blank\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(post("/api/securities").contentType(MediaType.APPLICATION_JSON).content(newSecurities))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;
	}

	@Test
		// name is not valid ru_RU
	void createSecurities_isBadRequest4() throws Exception {
		final String newSecurities = "{\n" +
				"    \"secId\": \"TEST1\",\n" +
				"    \"regNumber\": \"2-test-1\",\n" +
				"    \"name\": \"russian 1\",\n" +
				"    \"emitentTitle\": \"test1test1test1test1test1\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Not valid field NAME = russian 1\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(post("/api/securities").contentType(MediaType.APPLICATION_JSON).content(newSecurities))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;
	}

	@Test
	void updateSecurities_isOk() throws Exception {
		final String updateSecurities = "{\n" +
				"	 \"id\": 3,\n" +
				"    \"secId\": \"MTLRP\",\n" +
				"    \"regNumber\": \"test2-01-55005-E\",\n" +
				"    \"name\": \"Мечел ПАО ап\",\n" +
				"    \"emitentTitle\": \"---Публичное акционерное общество \\\"Мечел\\\"\"\n" +
				"}";


		mockMvc.perform(put("/api/securities").contentType(MediaType.APPLICATION_JSON).content(updateSecurities))
				.andExpect(status().isOk())
				.andExpect(content().json(updateSecurities))
		;
	}

	@Test
		// id == null
	void updateSecurities_isBadRequest0() throws Exception {
		final String updateSecurities = "{\n" +
				"    \"secId\": \"MTLRP\",\n" +
				"    \"regNumber\": \"test2-01-55005-E\",\n" +
				"    \"name\": \"Мечел ПАО ап\",\n" +
				"    \"emitentTitle\": \"---Публичное акционерное общество \\\"Мечел\\\"\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Update error: id = null\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(put("/api/securities").contentType(MediaType.APPLICATION_JSON).content(updateSecurities))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;
	}

	@Test
		// secId == null
	void updateSecurities_isBadRequest1() throws Exception {
		final String updateSecurities = "{\n" +
				"	 \"id\": 3,\n" +
				"    \"regNumber\": \"test2-01-55005-E\",\n" +
				"    \"name\": \"Мечел ПАО ап\",\n" +
				"    \"emitentTitle\": \"---Публичное акционерное общество \\\"Мечел\\\"\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Update error: secId = null\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(put("/api/securities").contentType(MediaType.APPLICATION_JSON).content(updateSecurities))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;
	}

	@Test
		// name or emitentTitle = blank
	void updateSecurities_isBadRequest2() throws Exception {
		final String updateSecurities = "{\n" +
				"	 \"id\": 3,\n" +
				"    \"secId\": \"MTLRP\",\n" +
				"    \"regNumber\": \"test2-01-55005-E\",\n" +
				"    \"name\": \"\",\n" +
				"    \"emitentTitle\": \"---Публичное акционерное общество \\\"Мечел\\\"\"\n" +
				"}";

		final String expected = "{\"status\":400,\"message\":\"Validation failed\",\"httpStatus\":\"BAD_REQUEST\"}";

		mockMvc.perform(put("/api/securities").contentType(MediaType.APPLICATION_JSON).content(updateSecurities))
				.andExpect(status().isBadRequest())
				.andExpect(content().json(expected))
		;

	}

	@Test
	void updateSecurities_isNotFound() throws Exception {
		final String updateSecurities = "{\n" +
				"	 \"id\": 3,\n" +
				"    \"secId\": \"QWER\",\n" +
				"    \"regNumber\": \"test2-01-55005-E\",\n" +
				"    \"name\": \"Мечел ПАО ап\",\n" +
				"    \"emitentTitle\": \"---Публичное акционерное общество \\\"Мечел\\\"\"\n" +
				"}";

		final String expected = "{\"status\":404,\"message\":\"Securities not found: SECID=QWER\",\"httpStatus\":\"NOT_FOUND\"}";

		mockMvc.perform(put("/api/securities").contentType(MediaType.APPLICATION_JSON).content(updateSecurities))
				.andExpect(status().is(404))
				.andExpect(content().json(expected))
		;
	}

	@Test
	void deleteSecurities_isOK() throws Exception {
		mockMvc.perform(delete("/api/securities/1"))
				.andExpect(status().isOk())
		;
	}

	@Test
	void deleteSecurities_isNotFound() throws Exception {
		mockMvc.perform(delete("/api/securities/10"))
				.andExpect(status().is(404))
		;
	}
}