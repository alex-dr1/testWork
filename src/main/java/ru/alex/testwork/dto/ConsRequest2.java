package ru.alex.testwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.alex.testwork.service.DirSort;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ConsRequest2 {

	String filterEmitentTitle;
	String filterTradeDate;
	List<DirSort> dirSorts;

}