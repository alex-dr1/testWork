package ru.alex.testwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.testwork.dto.HistoryDto;
import ru.alex.testwork.service.impl.HistoryServiceImpl;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

	final HistoryServiceImpl historyService;

	public HistoryController(HistoryServiceImpl historyService) {
		this.historyService = historyService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<HistoryDto> findHistoryById(@PathVariable Long id) {
		return status(HttpStatus.OK).body(historyService.findHistoryById(id));
	}

	@GetMapping("/sec-id/{secId}")
	public ResponseEntity<List<HistoryDto>> findHistoryBySecId(@PathVariable String secId) {
		return status(HttpStatus.OK).body(historyService.findAllHistoryBySecId(secId));
	}

	@PostMapping
	public ResponseEntity<HistoryDto> createHistory(@RequestBody HistoryDto dto) {
		return status(HttpStatus.CREATED).body(historyService.create(dto));
	}

	@PutMapping
	public ResponseEntity<HistoryDto> updateHistory(@RequestBody HistoryDto dto) {
		return status(HttpStatus.OK).body(historyService.update(dto));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteHistory(@PathVariable Long id) {
		return status(HttpStatus.OK).body("Successful delete history id: " + historyService.delete(id));
	}
}
