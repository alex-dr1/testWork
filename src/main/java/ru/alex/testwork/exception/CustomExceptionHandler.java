package ru.alex.testwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class CustomExceptionHandler {

	// 400 Bad request
	@ExceptionHandler(value = {BadRestRequestException.class,ValidationException.class})
	public ResponseEntity<CustomException> handler400RuntimeException(RuntimeException exception) {
		return getCustomExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST);
	}

	// 404 Not found
	@ExceptionHandler(value = {SecuritiesNotFoundException.class, SecuritiesBySecIdNotFoundException.class})
	public ResponseEntity<CustomException> handler404RuntimeException(RuntimeException exception) {
		return getCustomExceptionResponseEntity(exception, HttpStatus.NOT_FOUND);
	}

	// Other exception
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<CustomExceptionOther> handlerOtherRuntimeException(RuntimeException exception) {
		CustomExceptionOther ceo =
//				new CustomExceptionOther(exception.getMessage());
				new CustomExceptionOther("ERROR");
		return new ResponseEntity<>(ceo, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<CustomException> getCustomExceptionResponseEntity(RuntimeException exception, HttpStatus httpStatus) {
		CustomException customException = new CustomException(httpStatus.value(), httpStatus, exception.getMessage());
		return new ResponseEntity<>(customException, httpStatus);
	}

}
