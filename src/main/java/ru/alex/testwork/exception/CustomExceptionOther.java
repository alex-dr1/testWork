package ru.alex.testwork.exception;


public class CustomExceptionOther {
	final String message;

	public CustomExceptionOther(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
