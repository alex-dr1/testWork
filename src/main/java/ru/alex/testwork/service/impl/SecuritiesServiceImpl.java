package ru.alex.testwork.service.impl;

import org.springframework.stereotype.Service;
import ru.alex.testwork.domain.entity.SecuritiesEntity;
import ru.alex.testwork.domain.model.Securities;
import ru.alex.testwork.exception.BadRestRequestException;
import ru.alex.testwork.exception.SecuritiesNotFoundException;
import ru.alex.testwork.repository.SecuritiesRepo;
import ru.alex.testwork.service.SecuritiesService;

@Service
public class SecuritiesServiceImpl implements SecuritiesService {

	final SecuritiesRepo securitiesRepo;

	public SecuritiesServiceImpl(SecuritiesRepo securitiesRepo) {
		this.securitiesRepo = securitiesRepo;
	}

	public void saveAll(Iterable<SecuritiesEntity> entities) {
		securitiesRepo.saveAll(entities);
	}

	@Override
	public Securities findOneById(Long id) {
		return Securities.toModel(securitiesRepo.findById(id).orElseThrow(() -> new SecuritiesNotFoundException(id)));
	}

	@Override
	public Securities save(Securities model) {
		Long modelId = model.getId();
		if (model.getId() != null && securitiesRepo.existsById(modelId))
			throw new BadRestRequestException("Create error: Bad request");
		return Securities.toModel(securitiesRepo.save(SecuritiesEntity.toEntity(model)));
	}

	@Override
	public Securities update(Securities model) {
		Long modelId = model.getId();
		if (model.getId() == null) throw new BadRestRequestException("Update error: Bad request");
		if (model.getId() != null && !securitiesRepo.existsById(modelId))
			throw new SecuritiesNotFoundException(modelId);
		return Securities.toModel(securitiesRepo.save(SecuritiesEntity.toEntity(model)));
	}

	@Override
	public Long delete(Long id) {
		if (!securitiesRepo.existsById(id)) throw new SecuritiesNotFoundException(id);
		securitiesRepo.deleteById(id);
		return id;
	}
}
