package ru.alex.testwork.mapper;

import ru.alex.testwork.entity.Securities;
import ru.alex.testwork.controller.dto.SecuritiesDto;
import ru.alex.testwork.camelrouters.xml.securities.SecuritiesXml;

public class SecuritiesMapper {
	public static Securities xmlToEntity(SecuritiesXml xml) {
		Securities securities = new Securities();
		securities.setId(null);
		securities.setSecId(xml.getSecId());
		securities.setRegNumber(xml.getRegNumber());
		securities.setName(xml.getName());
		securities.setEmitentTitle(xml.getEmitentTitle());
		return securities;
	}

	public static Securities dtoToEntity(SecuritiesDto dto) {
		Securities securities = new Securities();
		securities.setId(dto.getId());
		securities.setSecId(dto.getSecId());
		securities.setRegNumber(dto.getRegNumber());
		securities.setName(dto.getName());
		securities.setEmitentTitle(dto.getEmitentTitle());
		return securities;
	}

	public static SecuritiesDto entityToDto(Securities entity){
		return new SecuritiesDto(entity.getId(),
				entity.getSecId(),
				entity.getRegNumber(),
				entity.getName(),
				entity.getEmitentTitle());
	}

}
