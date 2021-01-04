package ru.alex.testwork.domain.xml.securities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "rows")
public class SecuritiesListXml {

	private List<SecuritiesXml> securitiesXmlList = new ArrayList<>();

	public List<SecuritiesXml> getSecuritiesXmlList() {
		return securitiesXmlList;
	}

	@XmlElement(name = "row")
	public void setSecuritiesXmlList(List<SecuritiesXml> securitiesXmlList) {
		this.securitiesXmlList = securitiesXmlList;
	}



}
