package ru.alex.testwork.domain.entity;

import org.hibernate.annotations.NaturalId;
import ru.alex.testwork.domain.model.Securities;
import ru.alex.testwork.domain.xml.securities.SecuritiesXml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "securities")
public class SecuritiesEntity implements Serializable {

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

	@NotBlank(message = "name not blank")
	private String name;

	@NotBlank(message = "emitent title not blank")
	private String emitentTitle;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "securities", fetch = FetchType.LAZY)
	private Set<HistoryEntity> historySet;

	public SecuritiesEntity() {
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

	public static SecuritiesEntity toEntity(SecuritiesXml xml) {
		SecuritiesEntity securities = new SecuritiesEntity();
		securities.setId(null);
		securities.setSecId(xml.getSecId());
		securities.setRegNumber(xml.getRegNumber());
		securities.setName(xml.getName());
		securities.setEmitentTitle(xml.getEmitentTitle());
		return securities;
	}

	public static SecuritiesEntity toEntity(Securities model) {
		SecuritiesEntity securities = new SecuritiesEntity();
		securities.setId(model.getId());
		securities.setSecId(model.getSecId());
		securities.setRegNumber(model.getRegNumber());
		securities.setName(model.getName());
		securities.setEmitentTitle(model.getEmitentTitle());
		return securities;
	}
}
