package ru.alex.testwork.domain.model;

import ru.alex.testwork.domain.entity.SecuritiesEntity;

import javax.persistence.Column;

public class Securities {
	private final Long id;
	private final String secId;
	private final String regNumber;
	private final String name;
	private final String emitentTitle;

	public Securities(Long id, String secId, String regNumber, String name, String emitentTitle) {
		this.id = id;
		this.secId = secId;
		this.regNumber = regNumber;
		this.name = name;
		this.emitentTitle = emitentTitle;
	}

	public Long getId() {
		return id;
	}

	public String getSecId() {
		return secId;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public String getName() {
		return name;
	}

	public String getEmitentTitle() {
		return emitentTitle;
	}

	@Override
	public String toString() {
		return "Securities{" +
				"id=" + id +
				", secId='" + secId + '\'' +
				", regNumber='" + regNumber + '\'' +
				", name='" + name + '\'' +
				", emitentTitle='" + emitentTitle + '\'' +
				'}';
	}

	public static Securities toModel(SecuritiesEntity entity){
		return new Securities(entity.getId(),
				entity.getSecId(),
				entity.getRegNumber(),
				entity.getName(),
				entity.getEmitentTitle());
	}
}
