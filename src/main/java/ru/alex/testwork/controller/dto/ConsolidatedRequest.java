package ru.alex.testwork.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class ConsolidatedRequest {

	String filterEmitentTitle;
	String filterTradeDate;
	Map<String, String> sortField;

}