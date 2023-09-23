package com.bagus2x.serverapp.region;

import com.bagus2x.serverapp.region.dto.RegionCreationDto;
import com.bagus2x.serverapp.region.dto.RegionDto;
import com.bagus2x.serverapp.region.dto.RegionUpdateDto;
import com.bagus2x.serverapp.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegionService {
    private final RegionRepository regionRepository;
    private final Validator validator;
    private final Mapper mapper;

    public RegionService(RegionRepository regionRepository, Validator validator, Mapper mapper) {
        this.regionRepository = regionRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Transactional
    public RegionDto create(RegionCreationDto regionCreationDto) {
        Set<ConstraintViolation<RegionCreationDto>> constraintViolations = validator.validate(regionCreationDto);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (regionRepository.existsByNameIgnoreCase(regionCreationDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name already exists. Use another name");
        }

        Region region = new Region();
        region.setName(regionCreationDto.getName());

        region = regionRepository.save(region);

        return mapper.toDto(region);
    }

    public List<RegionDto> getAll() {
        return regionRepository
            .findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    public RegionDto getById(Integer regionId) {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found"));
        return mapper.toDto(region);
    }

    @Transactional
    public RegionDto update(Integer regionId, RegionUpdateDto dto) {
        Region region = regionRepository
            .findById(regionId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region is not found"));
        region.setName(dto.getName());

        region = regionRepository.save(region);

        return mapper.toDto(region);
    }

    @Transactional
    public RegionDto delete(Integer regionId) {
        Region region = regionRepository
            .findById(regionId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region is not found"));

        regionRepository.delete(region);

        return mapper.toDto(region);
    }
}