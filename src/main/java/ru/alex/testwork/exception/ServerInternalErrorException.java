package ru.alex.testwork.exception;

public class ServerInternalErrorException extends RuntimeException{
	public ServerInternalErrorException(String message) {
		super(message);
	}
}
