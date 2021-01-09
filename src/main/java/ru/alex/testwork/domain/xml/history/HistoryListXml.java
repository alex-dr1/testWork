package ru.alex.testwork.domain.xml.history;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "rows")
public class HistoryListXml {

	private List<HistoryXml> historyXmlList = new ArrayList<>();

	public List<HistoryXml> getHistoryXmlList() {
		return historyXmlList;
	}

	@XmlElement(name = "row")
	public void setHistoryXmlList(List<HistoryXml> historyXmlList) {
		this.historyXmlList = historyXmlList;
	}
}
