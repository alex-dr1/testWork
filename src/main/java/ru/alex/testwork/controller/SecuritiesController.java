package ru.alex.testwork.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.testwork.controller.dto.SecuritiesDto;
import ru.alex.testwork.service.impl.SecuritiesServiceImpl;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/api/securities", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SecuritiesController {

	final SecuritiesServiceImpl securitiesService;

	public SecuritiesController(SecuritiesServiceImpl securitiesService) {
		this.securitiesService = securitiesService;
	}


	@GetMapping
	public ResponseEntity<List<SecuritiesDto>> getAllSecurities() {
		return status(HttpStatus.OK).body(securitiesService.getAllSecurities());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SecuritiesDto> getOneById(@PathVariable Long id) {
		return status(HttpStatus.OK).body(securitiesService.findOneById(id));
	}

	@GetMapping(value = "/sec-id/{secId}")
	public ResponseEntity<SecuritiesDto> getOneBySecId(@PathVariable String secId) {
		return status(HttpStatus.OK).body(securitiesService.findOneBySecId(secId));
	}

	@PostMapping
	public ResponseEntity<SecuritiesDto> createSecurities(@Valid @RequestBody SecuritiesDto dto) {
		return status(HttpStatus.CREATED).body(securitiesService.save(dto));
	}

	@PutMapping
	public ResponseEntity<SecuritiesDto> updateSecurities(@RequestBody SecuritiesDto dto) {
		return status(HttpStatus.OK).body(securitiesService.update(dto));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteSecurities(@PathVariable Long id) {
		return status(HttpStatus.OK).body("Successful delete securities id: " + securitiesService.delete(id));
	}

}
