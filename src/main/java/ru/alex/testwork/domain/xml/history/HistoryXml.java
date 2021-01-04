package ru.alex.testwork.domain.xml.history;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryXml {
	/*
	 name="TRADEDATE" type="date" bytes="10" max_size="0"
	 name="SHORTNAME" type="string" bytes="189" max_size="0"
	 name="SECID" type="string" bytes="36" max_size="0"
	 name="NUMTRADES" type="double"
	 name="OPEN" type="double"
	 name="CLOSE" type="double"
	 */

	@XmlAttribute(name = "TRADEDATE")
	private String tradeDate;
	@XmlAttribute(name = "SHORTNAME")
	private String shortname;
	@XmlAttribute(name = "SECID")
	private String secId;
	@XmlAttribute(name = "NUMTRADES")
	private String numTrades;
	@XmlAttribute(name = "OPEN")
	private String open;
	@XmlAttribute(name = "CLOSE")
	private String close;

	public HistoryXml() {}

	public HistoryXml(String tradeDate, String shortname, String secId, String numTrades, String open, String close) {
		this.tradeDate = tradeDate;
		this.shortname = shortname;
		this.secId = secId;
		this.numTrades = numTrades;
		this.open = open;
		this.close = close;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public String getNumTrades() {
		return numTrades;
	}

	public void setNumTrades(String numTrades) {
		this.numTrades = numTrades;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	@Override
	public String toString() {
		return "HistoryXml{" +
				"tradeDate='" + tradeDate + '\'' +
				", shortname='" + shortname + '\'' +
				", secId='" + secId + '\'' +
				", numTrades='" + numTrades + '\'' +
				", open='" + open + '\'' +
				", close='" + close + '\'' +
				'}';
	}
}
