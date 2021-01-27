package ru.alex.testwork.exception;


import org.springframework.http.HttpStatus;

public class CustomException {
	final int status;
	final HttpStatus httpStatus;
	final String message;

	public CustomException(int status, HttpStatus httpStatus, String message) {
		this.status = status;
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}
}
