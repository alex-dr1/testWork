package ru.alex.testwork.service;

import ru.alex.testwork.controller.dto.ConsolidatedRequest;
import ru.alex.testwork.controller.dto.ConsolidatedDto;

import java.util.List;

public interface ConsolidatedService {
	List<ConsolidatedDto> getAllConsolidated(ConsolidatedRequest consRequest);
}
