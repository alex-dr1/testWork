package ru.alex.testwork.exception;

public class SecuritiesNotFoundException extends RuntimeException{
	public SecuritiesNotFoundException(Long id){
		super("Securities id not found: " + id);
	}
}

