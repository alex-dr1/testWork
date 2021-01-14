package ru.alex.testwork.domain.xml.securities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class SecuritiesXml {
	/*
	 <column name="secid" type="string" bytes="36" max_size="0"/>
	 <column name="shortname" type="string" bytes="189" max_size="0"/>
	 <column name="regnumber" type="string" bytes="189" max_size="0"/>
	 <column name="name" type="string" bytes="765" max_size="0"/>
	 <column name="emitent_title" type="string" bytes="765" max_size="0"/>
	 */
	@XmlAttribute(name = "secid")
	private String secId;
	@XmlAttribute(name = "regnumber")
	private String regNumber;
	@XmlAttribute(name = "name")
	private String name;
	@XmlAttribute(name = "emitent_title")
	private String emitentTitle;

	public SecuritiesXml() {
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

	@Override
	public String toString() {
		return "SecuritiesXml{" +
				"secId='" + secId + '\'' +
				", regNumber='" + regNumber + '\'' +
				", name='" + name + '\'' +
				", emitentTitle='" + emitentTitle + '\'' +
				'}';
	}
}
