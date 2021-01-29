package ru.alex.testwork.controller.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class ConsolidatedDto {

	String secId;
	String regNumber;
	String name;
	String emitentTitle;
	String tradeDate;
	String numTrades;
	String open;
	String close;

	public ConsolidatedDto(String secId, String regNumber, String name, String emitentTitle, LocalDate tradeDate, double numTrades, double open, double close) {
		this.secId = secId;
		this.regNumber = regNumber;
		this.name = name;
		this.emitentTitle = emitentTitle;
		this.tradeDate = tradeDate.toString();
		this.numTrades = String.valueOf(numTrades);
		this.open = String.valueOf(open);
		this.close = String.valueOf(close);
	}
}
