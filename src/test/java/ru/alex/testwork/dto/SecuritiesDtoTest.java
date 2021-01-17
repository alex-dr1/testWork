package ru.alex.testwork.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecuritiesDtoTest {

	public static final long ID = 1L;
	public static final String SEC_ID = "secId";
	public static final String REG_NUMBER = "regNumber";
	public static final String NAME = "name";
	public static final String EMITENT_TITLE = "emitentTitle";
	public static final int AMOUNT_FIELDS = 5;
	SecuritiesDto securities;

	@BeforeEach
	void setSecurities() {
		securities = new SecuritiesDto(ID, SEC_ID, REG_NUMBER, NAME, EMITENT_TITLE);
	}

	@Test
	void shouldRetrieveValidFields() {
		assertEquals(ID, securities.getId());
		assertEquals(SEC_ID, securities.getSecId());
		assertEquals(REG_NUMBER, securities.getRegNumber());
		assertEquals(NAME, securities.getName());
		assertEquals(EMITENT_TITLE, securities.getEmitentTitle());
	}

	@Test
	void shouldRetrieveValidAmountFields() {
		Class<?> xmlClass = securities.getClass();
		assertEquals(AMOUNT_FIELDS, xmlClass.getDeclaredFields().length);
	}

	@Test
	void shouldValidToString() {
		String expected = "SecuritiesDto{" +
				"id=" + ID +
				", secId='" + SEC_ID + '\'' +
				", regNumber='" + REG_NUMBER + '\'' +
				", name='" + NAME + '\'' +
				", emitentTitle='" + EMITENT_TITLE + '\'' +
				'}';
		assertEquals(expected, securities.toString());
	}
}