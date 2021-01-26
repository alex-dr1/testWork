package ru.alex.testwork.controller.dto;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ConsolidatedDto {

	private static final String DATA_PATTERN = "yyyy-MM-dd";
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATA_PATTERN);

	String secId;
	String regNumber;
	String name;
	String emitentTitle;
	String tradeDate;
	String numTrades;
	String open;
	String close;

	public ConsolidatedDto() {
	}

	public ConsolidatedDto(String secId, String regNumber, String name, String emitentTitle, Date tradeDate, double numTrades, double open, double close) {
		this.secId = secId;
		this.regNumber = regNumber;
		this.name = name;
		this.emitentTitle = emitentTitle;
		this.tradeDate = simpleDateFormat.format(tradeDate);
		this.numTrades = String.valueOf(numTrades);
		this.open = String.valueOf(open);
		this.close = String.valueOf(close);
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

	public String getTradeDate() {
		return tradeDate;
	}

	public String getNumTrades() {
		return numTrades;
	}

	public String getOpen() {
		return open;
	}

	public String getClose() {
		return close;
	}

	@Override
	public String toString() {
		return "ConsolidatedDto{" +
				"secId='" + secId + '\'' +
				", regNumber='" + regNumber + '\'' +
				", name='" + name + '\'' +
				", emitentTitle='" + emitentTitle + '\'' +
				", tradeDate='" + tradeDate + '\'' +
				", numTrades='" + numTrades + '\'' +
				", open='" + open + '\'' +
				", close='" + close + '\'' +
				'}';
	}
}
