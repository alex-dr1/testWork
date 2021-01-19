package ru.alex.testwork.service.impl;

import org.springframework.stereotype.Service;
import ru.alex.testwork.dto.SecuritiesDto;
import ru.alex.testwork.entity.SecuritiesEntity;
import ru.alex.testwork.exception.BadRestRequestException;
import ru.alex.testwork.exception.NameRuLangNotValidException;
import ru.alex.testwork.exception.SecuritiesBySecIdNotFoundException;
import ru.alex.testwork.exception.SecuritiesNotFoundException;
import ru.alex.testwork.mapper.SecuritiesMapper;
import ru.alex.testwork.repository.SecuritiesRepo;
import ru.alex.testwork.service.SecuritiesService;
import ru.alex.testwork.utils.HandValidator;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecuritiesServiceImpl implements SecuritiesService {

	final SecuritiesRepo securitiesRepo;
	final HandValidator validatorRuLang;

	public SecuritiesServiceImpl(SecuritiesRepo securitiesRepo, HandValidator validatorRuLang) {
		this.securitiesRepo = securitiesRepo;
		this.validatorRuLang = validatorRuLang;
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
		Optional<Long> id = Optional.ofNullable(dto.getId());
		Optional<String> secId = Optional.ofNullable(dto.getSecId());

		id.ifPresent(idSecurities -> {
			if (securitiesRepo.existsById(id.get()))
				throw new BadRestRequestException("Create error: id is exist");
			throw new BadRestRequestException("Create error: id !=null");
		});

		secId.orElseThrow(() -> new BadRestRequestException("Create error: secId == null"));
		if (securitiesRepo.existsBySecId(secId.get()))
			throw new BadRestRequestException("Create error: secId is exist");

		return SecuritiesMapper.entityToDto(securitiesRepo.save(SecuritiesMapper.dtoToEntity(dto)));
	}

	@Override
	public SecuritiesDto update(SecuritiesDto dto) {
		Optional<Long> id = Optional.ofNullable(dto.getId());
		Optional<String> secId = Optional.ofNullable(dto.getSecId());

		id.orElseThrow(() -> new BadRestRequestException("Update error: id = null"));
		if (!securitiesRepo.existsById(id.get()))
			throw new BadRestRequestException("Create error: id not is exist");
		secId.orElseThrow(() -> new BadRestRequestException("Update error: secId = null"));
		securitiesRepo.findSecuritiesBySecId(secId.get()).orElseThrow(() -> new SecuritiesBySecIdNotFoundException(secId.get()));

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
		SecuritiesEntity entity = securitiesRepo.findSecuritiesBySecId(secId).orElseThrow(() -> new SecuritiesBySecIdNotFoundException(secId));
		return SecuritiesMapper.entityToDto(entity);
	}

	public SecuritiesDto saveHand(SecuritiesDto dto) {
		if(!validatorRuLang.isValid(dto.getName())) throw new NameRuLangNotValidException(dto.getName());
		return this.save(dto);
	}

	public SecuritiesDto updateHand(SecuritiesDto dto) {
		if(!validatorRuLang.isValid(dto.getName())) throw new NameRuLangNotValidException(dto.getName());
		return this.update(dto);
	}
}
