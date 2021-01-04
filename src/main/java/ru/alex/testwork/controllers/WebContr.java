package ru.alex.testwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebContr {

	@GetMapping
	public String hello(){
		return "hello";
	}
}
