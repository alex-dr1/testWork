package ru.alex.testwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alex.testwork.service.ParseXmlService;

@Controller
public class IndexController {

	@GetMapping
	public String hello(){
		return "hello";
	}
}
