package ru.alex.testwork.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.testwork.service.ParseXmlService;

@Slf4j
@Service
@AllArgsConstructor
public class ParseServiceImpl implements ParseXmlService {

	private final ProducerTemplate template;

	@Override
	public String run() {
		String body = "Parse files:" + (String) template.requestBody("direct:parse", "");
		log.info(body);
		return body;
	}
}
