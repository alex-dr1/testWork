package ru.alex.testwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.testwork.dto.HistoryDto;
import ru.alex.testwork.service.impl.HistoryServiceImpl;

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
	public ResponseEntity<HistoryDto> findHistoryBySecId(@PathVariable String secId) {
		return status(HttpStatus.OK).body(historyService.findHistoryBySecId(secId));
	}

	@PostMapping("/{secId}")
	public ResponseEntity<HistoryDto> createHistory(@PathVariable String secId,
													@RequestBody HistoryDto dto) {

		return status(HttpStatus.CREATED).body(historyService.save(dto));
	}
}
