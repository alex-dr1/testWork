package ru.alex.testwork.service.impl;

import org.springframework.stereotype.Service;
import ru.alex.testwork.dto.HistoryDto;
import ru.alex.testwork.entity.HistoryEntity;
import ru.alex.testwork.entity.SecuritiesEntity;
import ru.alex.testwork.exception.BadRestRequestException;
import ru.alex.testwork.exception.HistoryBySecIdNotFoundException;
import ru.alex.testwork.exception.HistoryNotFoundException;
import ru.alex.testwork.mapper.HistoryMapper;
import ru.alex.testwork.repository.HistoryRepo;
import ru.alex.testwork.repository.SecuritiesRepo;
import ru.alex.testwork.service.HistoryService;
import ru.alex.testwork.service.MoexService;

@Service
public class HistoryServiceImpl implements HistoryService {
	final HistoryRepo historyRepo;
	final SecuritiesRepo securitiesRepo;
	final MoexService moexService;

	public HistoryServiceImpl(HistoryRepo historyRepo, SecuritiesRepo securitiesRepo, MoexService moexService) {
		this.historyRepo = historyRepo;
		this.securitiesRepo = securitiesRepo;
		this.moexService = moexService;
	}

	public void saveImport(HistoryEntity history) {
		SecuritiesEntity securities;
		String secId = history.getSecId();
		securities = securitiesRepo.findSecuritiesBySecId(secId).orElse(null);
		if (securities == null) {
			SecuritiesEntity securitiesMoex = moexService.fetchSecuritiesBySecId(secId);
			//TODO if null thrown exception
			if (securitiesMoex == null) return;
			securities = securitiesRepo.save(securitiesMoex);
		}
		history.setSecurities(securities);
		historyRepo.save(history);
	}

	@Override
	public HistoryDto findHistoryById(Long id) {
		HistoryEntity entity = historyRepo.findById(id).orElseThrow(() -> new HistoryNotFoundException(id));
		return HistoryMapper.entityToDto(entity);
	}

	@Override
	public HistoryDto findHistoryBySecId(String secId) {
		HistoryEntity entity = historyRepo.findBySecId(secId).orElseThrow(() -> new HistoryBySecIdNotFoundException(secId));
		return HistoryMapper.entityToDto(entity);
	}

	@Override
	public HistoryDto save(HistoryDto dto) {
		HistoryEntity entity = HistoryMapper.dtoToEntity(dto);
		String secId = entity.getSecId();

		if(secId == null) throw new BadRestRequestException("secId == null");

		SecuritiesEntity securities = securitiesRepo.findSecuritiesBySecId(secId).orElse(null);
		return null;
	}
}
