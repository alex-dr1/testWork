package ru.alex.testwork.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class SecuritiesId implements Serializable {

	private Long id;
	private String secId;

	public SecuritiesId() {
	}

	public SecuritiesId(Long id, String secId) {
		this.id = id;
		this.secId = secId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SecuritiesId)) return false;
		SecuritiesId that = (SecuritiesId) o;
		return Objects.equals(id, that.id) && Objects.equals(secId, that.secId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, secId);
	}
}
