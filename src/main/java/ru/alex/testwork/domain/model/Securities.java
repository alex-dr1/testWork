package ru.alex.testwork.domain.model;

import ru.alex.testwork.domain.entity.SecuritiesEntity;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Securities)) return false;
		Securities that = (Securities) o;
		return Objects.equals(id, that.id) && Objects.equals(secId, that.secId) && Objects.equals(regNumber, that.regNumber) && Objects.equals(name, that.name) && Objects.equals(emitentTitle, that.emitentTitle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, secId, regNumber, name, emitentTitle);
	}
}
