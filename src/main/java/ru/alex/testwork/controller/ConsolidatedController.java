package ru.alex.testwork.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.testwork.controller.dto.ConsolidatedDto;
import ru.alex.testwork.controller.dto.ConsolidatedRequest;
import ru.alex.testwork.service.impl.ConsolidatedServiceImpl;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/consolidated")
@AllArgsConstructor
public class ConsolidatedController {

	final ConsolidatedServiceImpl consolidatedService;

	@GetMapping
	public ResponseEntity<List<ConsolidatedDto>> getAllSecurities(@RequestBody ConsolidatedRequest consRequest) {
		return status(HttpStatus.OK).body(consolidatedService.getAllConsolidated(consRequest));
	}
}
