package ru.alex.testwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	// 404 Not found
	@ExceptionHandler(value = {SecuritiesNotFoundException.class})
	public ResponseEntity<CustomException> handler404RuntimeException(RuntimeException exception){
		return getCustomExceptionResponseEntity(exception, HttpStatus.NOT_FOUND);
	}

	// 500 Server Internal Error
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<CustomException> handler500RuntimeException(RuntimeException exception){
		return getCustomExceptionResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<CustomException> getCustomExceptionResponseEntity(RuntimeException exception, HttpStatus httpStatus) {
		CustomException customException = new CustomException(httpStatus.value(), httpStatus, exception.getMessage());
		return new ResponseEntity<>(customException, httpStatus);
	}

}
