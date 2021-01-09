package ru.alex.testwork.services;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.testwork.domain.entity.SecuritiesEntity;

@Service("moexService")
public class MoexServiceImpl implements MoexService {

	@Autowired
	private final ProducerTemplate producerTemplate;

	public MoexServiceImpl(ProducerTemplate producerTemplate) {
		this.producerTemplate = producerTemplate;
	}

	@Override
	public SecuritiesEntity fetchSecuritiesBySecId(String secId) {
		Object obj = producerTemplate.requestBody("direct:fetchSecuritiesMoexService", secId);
		if(obj instanceof SecuritiesEntity){
			return (SecuritiesEntity) obj;
		}
		return null;
	}
}
