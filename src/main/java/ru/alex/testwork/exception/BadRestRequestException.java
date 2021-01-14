package ru.alex.testwork.exception;

public class BadRestRequestException extends RuntimeException {
	public BadRestRequestException(String message) {
		super(message);
	}
}
