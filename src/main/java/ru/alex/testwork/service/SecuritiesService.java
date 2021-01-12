package ru.alex.testwork.service;

import org.springframework.stereotype.Service;
import ru.alex.testwork.domain.entity.SecuritiesEntity;
import ru.alex.testwork.domain.model.Securities;
import ru.alex.testwork.exception.SecuritiesNotFoundException;
import ru.alex.testwork.repository.SecuritiesRepo;

import java.util.function.Supplier;

@Service
public class SecuritiesService {

	final SecuritiesRepo securitiesRepo;

	public SecuritiesService(SecuritiesRepo securitiesRepo) {
		this.securitiesRepo = securitiesRepo;
	}

	public SecuritiesEntity saveEntity(SecuritiesEntity securitiesEntity) {
		return securitiesRepo.save(securitiesEntity);
	}

	public void saveAll(Iterable<SecuritiesEntity> entities) {
		securitiesRepo.saveAll(entities);
	}

	public Iterable<SecuritiesEntity> findAll() {
		return securitiesRepo.findAll();
	}

	public Securities findOneById(Long id) {
		return Securities.toModel(securitiesRepo.findById(id).orElseThrow(() -> new SecuritiesNotFoundException(id)));
	}

	public Securities save(Securities model) {
		return Securities.toModel(securitiesRepo.findById(model.getId())
				.orElseGet(() -> saveEntity(SecuritiesEntity.toEntity(model))));
	}
}
