package ru.alex.testwork.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "securities")
public class Securities implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@NaturalId
	@Column(name = "sec_id", nullable = false, unique = true)
	@Size(min = 4, message = "sec_id must be more than 3")
	private String secId;

	@NotNull
	private String regNumber;

	@NotNull
	@NotBlank(message = "name not blank")
	private String name;

	@NotNull
	@NotBlank(message = "emitent title not blank")
	private String emitentTitle;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "securities", fetch = FetchType.LAZY)
	private Set<History> historySet;

	public Securities() {
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
		return "\nSecurities{id=" + id +
				",\n           secId='" + secId + '\'' +
				",\n           regNumber='" + regNumber + '\'' +
				",\n           name='" + name + '\'' +
				",\n           emitentTitle='" + emitentTitle + '\'' +
				",\n           historySet=" + historySet +
				"\n          }\n";
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
