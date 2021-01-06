package ru.alex.testwork.services;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.testwork.domain.History;
import ru.alex.testwork.domain.Securities;

import java.sql.Date;

@Service("moexService")
public class MoexServiceImpl implements MoexService {

	@Autowired
	ProducerTemplate producerTemplate;

	@Override
	public Securities fetchSecuritiesBySecId(String secId) {
		Object obj = producerTemplate.requestBody("direct:fetchSecurities", secId);
		if(obj instanceof Securities){
			return (Securities) obj;
		}
		return null;
	}
}
