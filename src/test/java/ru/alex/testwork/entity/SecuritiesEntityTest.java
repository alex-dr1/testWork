package ru.alex.testwork.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecuritiesEntityTest {

	public static final long ID = 1L;
	public static final String SEC_ID = "SSSS";
	public static final String REG_NUMBER = "reg number";
	public static final String NAME = "name";
	public static final String EMITENT_TITLE = "emitent title";
	public static final int AMOUNT_FIELDS = 6;
	public static final Set<HistoryEntity> HISTORY_SET = Set.of(new HistoryEntity());
	SecuritiesEntity entity;

	@BeforeEach
	void setEntity() {
		entity = new SecuritiesEntity();
		entity.setEmitentTitle(EMITENT_TITLE);
		entity.setName(NAME);
		entity.setRegNumber(REG_NUMBER);
		entity.setSecId(SEC_ID);
		entity.setId(ID);
		entity.setHistorySet(HISTORY_SET);
	}

	@Test
	void shouldRetrieveValidFields() {
		assertEquals(ID, entity.getId());
		assertEquals(SEC_ID, entity.getSecId());
		assertEquals(REG_NUMBER, entity.getRegNumber());
		assertEquals(NAME, entity.getName());
		assertEquals(EMITENT_TITLE, entity.getEmitentTitle());
		assertEquals(HISTORY_SET, entity.getHistorySet());
	}

	@Test
	void shouldRetrieveValidAmountFields() {
		Class<?> xmlClass = entity.getClass();
		assertEquals(AMOUNT_FIELDS, xmlClass.getDeclaredFields().length);
	}

	@Test
	void shouldValidToString() {
		String expected = "SecuritiesEntity{" +
				"id=" + ID +
				", secId='" + SEC_ID + '\'' +
				", regNumber='" + REG_NUMBER + '\'' +
				", name='" + NAME + '\'' +
				", emitentTitle='" + EMITENT_TITLE + '\'' +
				", historySet=" + HISTORY_SET +
				'}';
		assertEquals(expected, entity.toString());
	}

}