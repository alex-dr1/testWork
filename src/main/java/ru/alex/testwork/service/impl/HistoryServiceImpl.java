package ru.alex.testwork.service.impl;

import org.springframework.stereotype.Service;
import ru.alex.testwork.dto.HistoryDto;
import ru.alex.testwork.entity.HistoryEntity;
import ru.alex.testwork.entity.SecuritiesEntity;
import ru.alex.testwork.exception.BadRestRequestException;
import ru.alex.testwork.exception.HistoryBySecIdNotFoundException;
import ru.alex.testwork.exception.HistoryNotFoundException;
import ru.alex.testwork.exception.SecuritiesBySecIdNotFoundException;
import ru.alex.testwork.mapper.HistoryMapper;
import ru.alex.testwork.repository.HistoryRepo;
import ru.alex.testwork.repository.SecuritiesRepo;
import ru.alex.testwork.service.HistoryService;
import ru.alex.testwork.service.MoexService;

import java.util.Optional;

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
	public HistoryDto create(HistoryDto dto) {
		Optional<Long> id = Optional.ofNullable(dto.getId());
		Optional<String> secId = Optional.ofNullable(dto.getSecId());
		HistoryEntity entity = HistoryMapper.dtoToEntity(dto);

		id.ifPresent(aLong -> {
			throw new BadRestRequestException("Create error: id != null");
		});
		secId.orElseThrow(() -> new BadRestRequestException("Create error: secId == null"));

		SecuritiesEntity securities = securitiesRepo.findSecuritiesBySecId(secId.get()).orElseThrow(() -> new SecuritiesBySecIdNotFoundException(secId.get()));

		entity.setSecurities(securities);
		return HistoryMapper.entityToDto(historyRepo.save(entity));
	}

	@Override
	public HistoryDto update(HistoryDto dto) {
		Optional<Long> id = Optional.ofNullable(dto.getId());
		Optional<String> secId = Optional.ofNullable(dto.getSecId());
		HistoryEntity entity = HistoryMapper.dtoToEntity(dto);

		id.orElseThrow(()->new BadRestRequestException("Update error: id == null"));
		secId.orElseThrow(()->new BadRestRequestException("Update error: secId == null"));

		SecuritiesEntity securities = securitiesRepo.findSecuritiesBySecId(secId.get()).orElseThrow(() -> new SecuritiesBySecIdNotFoundException(secId.get()));
		entity.setSecurities(securities);

		return HistoryMapper.entityToDto(historyRepo.save(entity));
	}

	@Override
	public Long delete(Long id) {
		if (!historyRepo.existsById(id)) throw new HistoryNotFoundException(id);
		historyRepo.deleteById(id);
		return id;
	}


}
