package ru.alex.testwork.exception;

public class NameRuLangNotValidException extends RuntimeException {
	public NameRuLangNotValidException(String message) {
		super("Not valid field NAME = " + message);
	}
}

