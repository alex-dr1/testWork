package ru.alex.testwork.exception;

public class SecuritiesNotFoundException extends RuntimeException {
	public SecuritiesNotFoundException(Long id) {
		super("Securities not found: ID=" + id);
	}
}

