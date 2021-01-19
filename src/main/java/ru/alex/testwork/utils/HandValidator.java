package ru.alex.testwork.utils;

import java.util.regex.Pattern;

public class HandValidator {
	final Pattern pattern;

	public HandValidator(String regex) {
		this.pattern = Pattern.compile(regex);
	}


	public boolean isValid(String string) {
		return pattern.matcher(string).matches();
	}
}
