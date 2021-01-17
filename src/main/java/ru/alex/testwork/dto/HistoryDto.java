package ru.alex.testwork.dto;

import ru.alex.testwork.entity.HistoryEntity;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class HistoryDto {

	private final Long id;
	private final String tradeDate;
	private final String secId;
	private final String numTrades;
	private final String open;
	private final String close;

	public HistoryDto(Long id, String tradeDate, String secId, String numTrades, String open, String close) {
		this.id = id;
		this.tradeDate = tradeDate;
		this.secId = secId;
		this.numTrades = numTrades;
		this.open = open;
		this.close = close;
	}

	public Long getId() {
		return id;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public String getSecId() {
		return secId;
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
		return "HistoryDto{" +
				"id=" + id +
				", tradeDate='" + tradeDate + '\'' +
				", secId='" + secId + '\'' +
				", numTrades='" + numTrades + '\'' +
				", open='" + open + '\'' +
				", close='" + close + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof HistoryDto)) return false;
		HistoryDto history = (HistoryDto) o;
		return Objects.equals(id, history.id) && Objects.equals(tradeDate, history.tradeDate) && Objects.equals(secId, history.secId) && Objects.equals(numTrades, history.numTrades) && Objects.equals(open, history.open) && Objects.equals(close, history.close);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tradeDate, secId, numTrades, open, close);
	}
}
