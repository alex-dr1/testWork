package ru.alex.testwork.domain;


import java.sql.Date;
import java.util.Objects;

public class History {

	private Long id;
	private Date tradeDate;
	private String shortname;
	private String secId;
	private double numTrades;
	private double open;
	private double close;
	private Securities securities;

	public History() {
	}

	public History(Long id, Date tradeDate, String shortname, String secId, double numTrades, double open, double close) {
		this.id = id;
		this.tradeDate = tradeDate;
		this.shortname = shortname;
		this.secId = secId;
		this.numTrades = numTrades;
		this.open = open;
		this.close = close;
	}

	public History(Long id, Date tradeDate, String shortname, String secId, double numTrades, double open, double close, Securities securities) {
		this.id = id;
		this.tradeDate = tradeDate;
		this.shortname = shortname;
		this.secId = secId;
		this.numTrades = numTrades;
		this.open = open;
		this.close = close;
		this.securities = securities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
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

	public double getNumTrades() {
		return numTrades;
	}

	public void setNumTrades(double numTrades) {
		this.numTrades = numTrades;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public Securities getSecurities() {
		return securities;
	}

	public void setSecurities(Securities securities) {
		this.securities = securities;
	}

	@Override
	public String toString() {
		return "History{" +
				"id=" + id +
				", tradeDate=" + tradeDate +
				", shortname='" + shortname + '\'' +
				", secId='" + secId + '\'' +
				", numTrades=" + numTrades +
				", open=" + open +
				", close=" + close +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof History)) return false;
		History history = (History) o;
		return Objects.equals(tradeDate, history.tradeDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tradeDate);
	}
}
