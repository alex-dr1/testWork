package ru.alex.testwork.exception;

public class HistoryBySecIdNotFoundException extends RuntimeException {
	public HistoryBySecIdNotFoundException(String secId) {
		super("History not found: SECID=" + secId);
	}
}
