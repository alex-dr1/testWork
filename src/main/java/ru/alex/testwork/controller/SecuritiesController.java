package ru.alex.testwork.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.testwork.domain.model.Securities;
import ru.alex.testwork.service.SecuritiesService;

@RestController
@RequestMapping("/securities")
public class SecuritiesController {

	final SecuritiesService securitiesService;

	public SecuritiesController(SecuritiesService securitiesService) {
		this.securitiesService = securitiesService;
	}

	@GetMapping(value = "/{id}")
	public Securities getOneById(@PathVariable Long id) {
		return securitiesService.findOneById(id);
	}
}
