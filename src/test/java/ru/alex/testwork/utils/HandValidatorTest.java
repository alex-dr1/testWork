package ru.alex.testwork.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandValidatorTest {

	HandValidator handValidator;

	@BeforeEach
	void setHandValidator() {
		handValidator = new HandValidator("^[а-яА-Я0-9\\s]*$");
	}

	@Test
	void shouldReturnTrue_isValid(){
		String string = "новый текст ТЕСТ 45344";
		assertTrue(handValidator.isValid(string));
	}

	@Test
	void shouldReturnFalse_isValid(){
		String string = "новый текст TEST 4325";
		assertFalse(handValidator.isValid(string));
	}
}