package ru.alex.testwork.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alex.testwork.utils.HandValidator;

@Configuration
public class HandValidatorConfig {

	@Bean
	public HandValidator getHandValidatorRuLang(){
		String regexPattern = "^[а-яА-Я0-9\\s]*$";
		return new HandValidator(regexPattern);
	}
}
