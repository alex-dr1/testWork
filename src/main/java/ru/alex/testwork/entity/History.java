package ru.alex.testwork.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "history")
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Date tradeDate;

	@Column(name = "sec_id", insertable = false, updatable = false, nullable = false)
	private String secId;

	@NotNull
	private Double numTrades;

	@NotNull
	private Double open;

	@NotNull
	private Double close;

	@ManyToOne
	@JoinColumn(name = "sec_id", referencedColumnName = "sec_id")
	private Securities securities;

	public History() {
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
		return "\nHistory{" +
				"id=" + id +
				", tradeDate=" + tradeDate +
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
