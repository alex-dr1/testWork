package ru.alex.testwork;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ru.alex.testwork.service.ParseXmlService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@Component
class RunApplication implements CommandLineRunner{

	final ParseXmlService parse;

	RunApplication(ParseXmlService parse) {
		this.parse = parse;
	}

	@Override
	public void run(String... args) throws Exception {
//		parse.run();
	}
}
