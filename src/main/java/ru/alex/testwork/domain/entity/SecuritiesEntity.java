package ru.alex.testwork.domain.entity;

import ru.alex.testwork.domain.xml.history.HistoryXml;
import ru.alex.testwork.domain.xml.securities.SecuritiesXml;

import java.util.*;

public class SecuritiesEntity {

	private Long id;
	private String secId;
	private String regNumber;
	private String name;
	private String emitentTitle;
	private Set<HistoryEntity> historySet = new HashSet<>();

	public SecuritiesEntity() {
	}

	public boolean addHistory(HistoryEntity history) {
		if (history.getSecId().equals(secId)) {
			historySet.add(history);
			history.setSecurities(this);
			return true;
		} else
			return false;

	}

	public void removeHistory(HistoryEntity history) {
		historySet.remove(history);
		history.setSecurities(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmitentTitle() {
		return emitentTitle;
	}

	public void setEmitentTitle(String emitentTitle) {
		this.emitentTitle = emitentTitle;
	}

	public Set<HistoryEntity> getHistorySet() {
		return historySet;
	}

	public void setHistorySet(Set<HistoryEntity> historySet) {
		this.historySet = historySet;
	}

	@Override
	public String toString() {
		return "SecuritiesEntity{" +
				"id=" + id +
				", secId='" + secId + '\'' +
				", regNumber='" + regNumber + '\'' +
				", name='" + name + '\'' +
				", emitentTitle='" + emitentTitle + '\'' +
				", historySet=" + historySet +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SecuritiesEntity)) return false;
		SecuritiesEntity that = (SecuritiesEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(secId, that.secId) && Objects.equals(regNumber, that.regNumber) && Objects.equals(name, that.name) && Objects.equals(emitentTitle, that.emitentTitle) && Objects.equals(historySet, that.historySet);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, secId, regNumber, name, emitentTitle, historySet);
	}

	public static SecuritiesEntity xmlToEntity(SecuritiesXml xml) {
		SecuritiesEntity securities = new SecuritiesEntity();
		securities.setId(xml.getId());
		securities.setSecId(xml.getSecId());
		securities.setRegNumber(xml.getRegNumber());
		securities.setName(xml.getName());
		securities.setEmitentTitle(xml.getEmitentTitle());
		return securities;
	}
}
