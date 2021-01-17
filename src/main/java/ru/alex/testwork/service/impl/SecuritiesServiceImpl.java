package ru.alex.testwork.service.impl;

import org.springframework.stereotype.Service;
import ru.alex.testwork.entity.SecuritiesEntity;
import ru.alex.testwork.dto.SecuritiesDto;
import ru.alex.testwork.exception.BadRestRequestException;
import ru.alex.testwork.exception.SecuritiesBySecIdNotFoundException;
import ru.alex.testwork.exception.SecuritiesNotFoundException;
import ru.alex.testwork.mapper.SecuritiesMapper;
import ru.alex.testwork.repository.SecuritiesRepo;
import ru.alex.testwork.service.SecuritiesService;

import java.util.List;
import java.util.stream.Collectors;

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
	public SecuritiesDto findOneById(Long id) {
		return SecuritiesMapper.entityToDto(securitiesRepo.findById(id).orElseThrow(() -> new SecuritiesNotFoundException(id)));
	}

	@Override
	public SecuritiesDto save(SecuritiesDto dto) {
		Long dtoId = dto.getId();
		if (dto.getId() != null && securitiesRepo.existsById(dtoId))
			throw new BadRestRequestException("Create error: Bad request");
		return SecuritiesMapper.entityToDto(securitiesRepo.save(SecuritiesMapper.dtoToEntity(dto)));
	}

	@Override
	public SecuritiesDto update(SecuritiesDto dto) {
		Long dtoId = dto.getId();
		String dtoSecId = dto.getSecId();

		if (dtoId == null) throw new BadRestRequestException("Update error: id = null");
		if (dtoSecId == null) throw new BadRestRequestException("Update error: secId = null");
		securitiesRepo.findSecuritiesBySecId(dtoSecId).orElseThrow(()-> new SecuritiesBySecIdNotFoundException(dtoSecId));

		return SecuritiesMapper.entityToDto(securitiesRepo.save(SecuritiesMapper.dtoToEntity(dto)));
	}

	@Override
	public Long delete(Long id) {
		if (!securitiesRepo.existsById(id)) throw new SecuritiesNotFoundException(id);
		securitiesRepo.deleteById(id);
		return id;
	}

	@Override
	public List<SecuritiesDto> getAllSecurities() {
		return securitiesRepo.findAll()
				.stream()
				.map(SecuritiesMapper::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public SecuritiesDto findOneBySecId(String secId) {
		SecuritiesEntity entity = securitiesRepo.findSecuritiesBySecId(secId).orElseThrow(()->new SecuritiesBySecIdNotFoundException(secId));
		return SecuritiesMapper.entityToDto(entity);
	}
}
