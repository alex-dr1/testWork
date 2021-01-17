package ru.alex.testwork.xml.securities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecuritiesXmlTest {

	public static final String SEC_ID = "SecId";
	public static final String EMITENT_TITLE = "EmitentTitle";
	public static final String NAME = "Name";
	public static final String REG_NUMBER = "RegName";
	public static final int AMOUNT_FIELDS = 4;
	SecuritiesXml securitiesXml;

	@BeforeEach
	void setUp() {
		securitiesXml = new SecuritiesXml();
		securitiesXml.setSecId(SEC_ID);
		securitiesXml.setEmitentTitle(EMITENT_TITLE);
		securitiesXml.setName(NAME);
		securitiesXml.setRegNumber(REG_NUMBER);

	}

	@Test
	void shouldRetrieveValidFieldSecId() {
		assertEquals(SEC_ID, securitiesXml.getSecId());
	}

	@Test
	void shouldRetrieveValidFieldEmitentTitle() {
		assertEquals(EMITENT_TITLE, securitiesXml.getEmitentTitle());
	}

	@Test
	void shouldRetrieveValidFieldName() {
		assertEquals(NAME, securitiesXml.getName());
	}

	@Test
	void shouldRetrieveValidFieldRegNumber() {
		assertEquals(REG_NUMBER, securitiesXml.getRegNumber());
	}

	@Test
	void shouldRetrieveValidAmountFields() {
		Class<?> xmlClass = securitiesXml.getClass();
		assertEquals(AMOUNT_FIELDS, xmlClass.getDeclaredFields().length);
	}

	@Test
	void shouldValidToString() {
		String expected = "SecuritiesXml{" +
				"secId='" + SEC_ID + '\'' +
				", regNumber='" + REG_NUMBER + '\'' +
				", name='" + NAME + '\'' +
				", emitentTitle='" + EMITENT_TITLE + '\'' +
				'}';
		assertEquals(expected, securitiesXml.toString());
	}
}