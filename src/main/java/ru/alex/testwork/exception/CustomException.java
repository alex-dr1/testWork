package ru.alex.testwork.exception;


import org.springframework.http.HttpStatus;

public class CustomException {
	final int status;
	final HttpStatus http_status;
	final String message;

	public CustomException(int status, HttpStatus http_status, String message) {
		this.status = status;
		this.http_status = http_status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public HttpStatus getHttpStatus() {
		return http_status;
	}

	public String getMessage() {
		return message;
	}
}
