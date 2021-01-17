package ru.alex.testwork.mapper;

import ru.alex.testwork.entity.SecuritiesEntity;
import ru.alex.testwork.dto.SecuritiesDto;
import ru.alex.testwork.xml.securities.SecuritiesXml;

public class SecuritiesMapper {
	public static SecuritiesEntity xmlToEntity(SecuritiesXml xml) {
		SecuritiesEntity securities = new SecuritiesEntity();
		securities.setId(null);
		securities.setSecId(xml.getSecId());
		securities.setRegNumber(xml.getRegNumber());
		securities.setName(xml.getName());
		securities.setEmitentTitle(xml.getEmitentTitle());
		return securities;
	}

	public static SecuritiesEntity dtoToEntity(SecuritiesDto dto) {
		SecuritiesEntity securities = new SecuritiesEntity();
		securities.setId(dto.getId());
		securities.setSecId(dto.getSecId());
		securities.setRegNumber(dto.getRegNumber());
		securities.setName(dto.getName());
		securities.setEmitentTitle(dto.getEmitentTitle());
		return securities;
	}

	public static SecuritiesDto entityToDto(SecuritiesEntity entity){
		return new SecuritiesDto(entity.getId(),
				entity.getSecId(),
				entity.getRegNumber(),
				entity.getName(),
				entity.getEmitentTitle());
	}

}
