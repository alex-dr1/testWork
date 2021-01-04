package ru.alex.testwork.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

public class Securities {

	private Long id;
	private String secId;
	private String regNumber;
	private String name;
	private String emitentTitle;
	private Set<History> historySet = new HashSet<>();

	public Securities() {
	}

	public Securities(Long id, String secId, String regNumber, String name, String emitentTitle) {
		this.id = id;
		this.secId = secId;
		this.regNumber = regNumber;
		this.name = name;
		this.emitentTitle = emitentTitle;
	}

	public Securities(Long id, String secId, String regNumber, String name, String emitentTitle, Set<History> historySet) {
		this.id = id;
		this.secId = secId;
		this.regNumber = regNumber;
		this.name = name;
		this.emitentTitle = emitentTitle;
		this.historySet = historySet;
	}

	public boolean addHistory(History history) {
		if (history.getSecId().equals(secId)) {
			historySet.add(history);
			history.setSecurities(this);
			return true;
		} else
			return false;

	}

	public void removeHistory(History history) {
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

	public Set<History> getHistorySet() {
		return historySet;
	}

	public void setHistorySet(Set<History> historySet) {
		this.historySet = historySet;
	}

	@Override
	public String toString() {
		return "Securities{" +
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
		if (!(o instanceof Securities)) return false;
		Securities that = (Securities) o;
		return Objects.equals(id, that.id) && Objects.equals(secId, that.secId) && Objects.equals(regNumber, that.regNumber) && Objects.equals(name, that.name) && Objects.equals(emitentTitle, that.emitentTitle) && Objects.equals(historySet, that.historySet);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, secId, regNumber, name, emitentTitle, historySet);
	}
}
