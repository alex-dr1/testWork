package ru.alex.testwork.exception.handler;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alex.testwork.exception.*;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@ControllerAdvice
public class CustomExceptionHandler {

	// 400 Bad request
	@ExceptionHandler(value = {BadRestRequestException.class, NameRuLangNotValidException.class})
	public ResponseEntity<CustomException> handler400RuntimeException(RuntimeException exception) {
		return getCustomExceptionResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// 400 Bad request(Validation)
	@ExceptionHandler(value = {ValidationException.class})
	public ResponseEntity<CustomException> handler400ValidationRuntimeException(RuntimeException exception) {
		String message = "Validation failed";
		if(exception instanceof ConstraintViolationException){
			ConstraintViolationImpl<?> violation = (ConstraintViolationImpl<?>) ((ConstraintViolationException) exception).getConstraintViolations().toArray()[0];
			message = violation.getMessageTemplate();
		}
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

	// Other exception
//	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<CustomException> handlerOtherRuntimeException(RuntimeException exception) {
//		return getCustomExceptionResponseEntity("server internal error", HttpStatus.INTERNAL_SERVER_ERROR);
		return getCustomExceptionResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<CustomException> getCustomExceptionResponseEntity(String message, HttpStatus httpStatus) {
		CustomException customException = new CustomException(httpStatus.value(), httpStatus, message);
		return new ResponseEntity<>(customException, httpStatus);
	}

}
