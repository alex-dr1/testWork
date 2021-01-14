package ru.alex.testwork.service.impl;

import org.springframework.stereotype.Service;
import ru.alex.testwork.domain.entity.HistoryEntity;
import ru.alex.testwork.domain.entity.SecuritiesEntity;
import ru.alex.testwork.repository.HistoryRepo;
import ru.alex.testwork.repository.SecuritiesRepo;
import ru.alex.testwork.service.MoexService;

@Service
public class HistoryServiceImpl {
	final HistoryRepo historyRepo;
	final SecuritiesRepo securitiesRepo;
	final MoexService moexService;

	public HistoryServiceImpl(HistoryRepo historyRepo, SecuritiesRepo securitiesRepo, MoexService moexService) {
		this.historyRepo = historyRepo;
		this.securitiesRepo = securitiesRepo;
		this.moexService = moexService;
	}

	public void save(HistoryEntity history) {
		SecuritiesEntity securities;
		String secId = history.getSecId();
		securities = securitiesRepo.findSecuritiesBySecId(secId);
		if(securities == null) {
			SecuritiesEntity securitiesMoex = moexService.fetchSecuritiesBySecId(secId);
			//TODO if null thrown exception
			if (securitiesMoex == null) return;
			securities = securitiesRepo.save(securitiesMoex);
		}
		history.setSecurities(securities);
		historyRepo.save(history);
	}
}
