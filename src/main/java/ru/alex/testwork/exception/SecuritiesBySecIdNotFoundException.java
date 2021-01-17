package ru.alex.testwork.exception;

public class SecuritiesBySecIdNotFoundException extends RuntimeException {
	public SecuritiesBySecIdNotFoundException(String secId) {
		super("Securities not found: SECID=" + secId);
	}
}
