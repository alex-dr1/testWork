package ru.alex.testwork.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.testwork.dto.ConsRequest;
import ru.alex.testwork.dto.ConsRequest2;
import ru.alex.testwork.service.DirSort;
import ru.alex.testwork.service.impl.ConsolidatedServiceImpl;

import java.text.ParseException;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/consolidated")
@AllArgsConstructor
public class ConsolidatedController {

	final ConsolidatedServiceImpl service;

	@GetMapping
	public ResponseEntity<String> getAllSecurities() {


		return status(HttpStatus.OK).body("OK");
	}
	@PostMapping
	public ResponseEntity<String> postAllSecurities(@RequestBody ConsRequest consRequest) throws ParseException {
/**
 * {
 *     "filterEmitentTitle": "em",
 *     "filterTradeDate": "2021-01-23",
 *     "sortField": {
 *         "name3": "ASC",
 *         "name2": "DESC",
 *         "name1": "ASC"
 *     }
 * }
 */

		service.getConsolidatedTable(consRequest);

		return status(HttpStatus.OK).body("consRequest");
	}
}
