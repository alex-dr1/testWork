package ru.alex.testwork.service;


import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParseServiceImpl implements ParseXmlService {

	@Autowired
	private final ProducerTemplate template;

	public ParseServiceImpl(ProducerTemplate template) {
		this.template = template;
	}


	@Override
	public String run() {
		return (String) template.requestBody("direct:parse", "");
	}
}
