package ru.alex.testwork.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alex.testwork.controller.dto.SecuritiesDto;
import ru.alex.testwork.entity.History;
import ru.alex.testwork.entity.Securities;
import ru.alex.testwork.camelrouters.xml.securities.SecuritiesXml;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SecuritiesMapperTest {

	public static final long ID = 1L;
	public static final String SEC_ID = "SSSS";
	public static final String REG_NUMBER = "reg number";
	public static final String NAME = "name";
	public static final String EMITENT_TITLE = "emitent title";
	public static final Set<History> HISTORY_SET = Set.of(new History());
	Securities entity;
	SecuritiesDto dto;

	@BeforeEach
	void setEntityAndDto() {
		entity = new Securities();
		entity.setEmitentTitle(EMITENT_TITLE);
		entity.setName(NAME);
		entity.setRegNumber(REG_NUMBER);
		entity.setSecId(SEC_ID);
		entity.setId(ID);
		entity.setHistorySet(HISTORY_SET);

		dto = new SecuritiesDto(ID, SEC_ID, REG_NUMBER, NAME, EMITENT_TITLE);
	}

	@Test
	void xmlToEntity() {
		SecuritiesXml xml = new SecuritiesXml();
		xml.setSecId(SEC_ID);
		xml.setEmitentTitle(EMITENT_TITLE);
		xml.setName(NAME);
		xml.setRegNumber(REG_NUMBER);

		entity.setHistorySet(null);
		entity.setId(null);
		assertEquals(entity, SecuritiesMapper.xmlToEntity(xml));

	}

	@Test
	void dtoToEntity() {
		entity.setHistorySet(null);
		assertEquals(entity, SecuritiesMapper.dtoToEntity(dto));
	}

	@Test
	void entityToDto() {
		assertEquals(dto, SecuritiesMapper.entityToDto(entity));
	}
}