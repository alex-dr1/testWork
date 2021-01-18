package ru.alex.testwork.exception;

public class HistoryNotFoundException extends RuntimeException {
	public HistoryNotFoundException(Long id) {
		super("History not found: ID=" + id);
	}
}

