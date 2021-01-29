package ru.alex.testwork.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.testwork.entity.Securities;
import ru.alex.testwork.service.MoexService;

import java.util.Optional;

@Slf4j
@Service("moexService")
public class MoexServiceImpl implements MoexService {

	@Autowired
	private final ProducerTemplate producerTemplate;

	public MoexServiceImpl(ProducerTemplate producerTemplate) {
		this.producerTemplate = producerTemplate;
	}

	@Override
	public Optional<Securities> fetchSecuritiesBySecId(String secId) {
		log.info("Request to iss.moex.com. Fetch info Securities SEC_ID=" + secId);
		Object obj = producerTemplate.requestBody("direct:fetchSecuritiesMoexService", secId);
		if(obj instanceof Securities){
			return Optional.of((Securities) obj);
		}
		return Optional.empty();
	}
}
