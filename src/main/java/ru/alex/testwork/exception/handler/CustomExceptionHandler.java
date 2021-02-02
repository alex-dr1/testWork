package ru.alex.testwork.exception.handler;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alex.testwork.exception.*;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@ControllerAdvice
public class CustomExceptionHandler {

	// 400 Bad request
	@ExceptionHandler(value = {BadRestRequestException.class,
			NameRuLangNotValidException.class, Exception.class})
	public ResponseEntity<CustomException> handler400RuntimeException(RuntimeException exception) {
		return getCustomExceptionResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// 400 Bad request(Validation)
	@ExceptionHandler(value = {ConstraintViolationException.class})
	public ResponseEntity<CustomException> handlerConstraintViolationException(ConstraintViolationException exception) {
		ConstraintViolationImpl<?> violation = (ConstraintViolationImpl<?>) exception.getConstraintViolations().toArray()[0];
		String message = violation.getMessageTemplate();
		return getCustomExceptionResponseEntity(message, HttpStatus.BAD_REQUEST);
	}

	// 400 Bad request(Validation)
	@ExceptionHandler(value = {ValidationException.class})
	public ResponseEntity<CustomException> handlerValidationException(ValidationException exception) {
		String message = "Validation failed";
		return getCustomExceptionResponseEntity(message, HttpStatus.BAD_REQUEST);
	}

	// 400 Bad request(Validation)
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<CustomException> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		String message = "Validation failed for field 'name'";
		return getCustomExceptionResponseEntity(message, HttpStatus.BAD_REQUEST);
	}

	// 400 Bad request(Validation)
	@ExceptionHandler(value = {TransactionSystemException.class})
	public ResponseEntity<CustomException> handlerTransactionSystemException(TransactionSystemException exception) {
		String message = "Error while committing the transaction";
		return getCustomExceptionResponseEntity(message, HttpStatus.BAD_REQUEST);
	}

	// 404 Not found
	@ExceptionHandler(value = {SecuritiesNotFoundException.class,
			SecuritiesBySecIdNotFoundException.class,
			HistoryNotFoundException.class,
			HistoryBySecIdNotFoundException.class})
	public ResponseEntity<CustomException> handler404RuntimeException(RuntimeException exception) {
		return getCustomExceptionResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<CustomException> getCustomExceptionResponseEntity(String message, HttpStatus httpStatus) {
		CustomException customException = new CustomException(httpStatus.value(), httpStatus, message);
		return new ResponseEntity<>(customException, httpStatus);
	}


}
