package ru.alex.testwork.service.impl;


import lombok.AllArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.testwork.service.ParseXmlService;

@Service
@AllArgsConstructor
public class ParseServiceImpl implements ParseXmlService {

	private final ProducerTemplate template;

	@Override
	public String run() {
		return (String) template.requestBody("direct:parse", "");
	}
}
