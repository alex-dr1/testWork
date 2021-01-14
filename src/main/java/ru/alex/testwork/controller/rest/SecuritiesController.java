package ru.alex.testwork.controller.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.testwork.domain.model.Securities;
import ru.alex.testwork.service.impl.SecuritiesServiceImpl;

@RestController
@RequestMapping("/securities")
public class SecuritiesController {

	final SecuritiesServiceImpl securitiesService;

	public SecuritiesController(SecuritiesServiceImpl securitiesService) {
		this.securitiesService = securitiesService;
	}

	@GetMapping(value = "/{id}")
	public Securities getOneById(@PathVariable Long id) {
		return securitiesService.findOneById(id);
	}

	@PostMapping(value = "/add")
	public Securities createSecurities(@RequestBody Securities model){
		return securitiesService.save(model);
	}

	@PutMapping(value = "/update")
	public Securities updateSecurities(@RequestBody Securities model){
		return securitiesService.update(model);
	}

	@DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Object> deleteSecurities(@PathVariable Long id){
		return ResponseEntity.ok("Successful delete securities id: " + securitiesService.delete(id));
	}
}
