package ru.alex.testwork.domain.model;

import ru.alex.testwork.domain.entity.HistoryEntity;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class History {

	private static final String DATA_PATTERN = "yyyy-MM-dd";

	private final Long id;
	private final String tradeDate;
	private final String secId;
	private final String numTrades;
	private final String open;
	private final String close;

	public History(Long id, String tradeDate, String secId, String numTrades, String open, String close) {
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
		return "History{" +
				"id=" + id +
				", tradeDate='" + tradeDate + '\'' +
				", secId='" + secId + '\'' +
				", numTrades='" + numTrades + '\'' +
				", open='" + open + '\'' +
				", close='" + close + '\'' +
				'}';
	}

	public static History toModel(HistoryEntity entity) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATA_PATTERN);

		return new History(entity.getId(),
				simpleDateFormat.format(entity.getTradeDate()),
				entity.getSecId(),
				String.valueOf(entity.getNumTrades()),
				String.valueOf(entity.getOpen()),
				String.valueOf(entity.getClose()));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof History)) return false;
		History history = (History) o;
		return Objects.equals(id, history.id) && Objects.equals(tradeDate, history.tradeDate) && Objects.equals(secId, history.secId) && Objects.equals(numTrades, history.numTrades) && Objects.equals(open, history.open) && Objects.equals(close, history.close);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tradeDate, secId, numTrades, open, close);
	}
}
