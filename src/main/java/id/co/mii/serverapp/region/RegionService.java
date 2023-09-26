package id.co.mii.serverapp.region;

import id.co.mii.serverapp.country.CountryRepository;
import id.co.mii.serverapp.region.dto.RegionCreationDto;
import id.co.mii.serverapp.region.dto.RegionDto;
import id.co.mii.serverapp.region.dto.RegionUpdateDto;
import id.co.mii.serverapp.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
    private final CountryRepository countryRepository;
    private final Validator validator;
    private final Mapper mapper;

    public RegionService(RegionRepository regionRepository, CountryRepository countryRepository, Validator validator, Mapper mapper) {
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    public RegionDto create(RegionCreationDto regionCreationDto) {
        Set<ConstraintViolation<RegionCreationDto>> constraintViolations = validator.validate(regionCreationDto);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (regionRepository.existsByNameIgnoreCase(regionCreationDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name already exists. Use another name");
        }

        if (countryRepository.existsByNameIgnoreCase(regionCreationDto.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The region name and the country name must not be the same");
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

    public RegionDto update(Integer regionId, RegionUpdateDto regionUpdateDto) {
        Region region = regionRepository
            .findById(regionId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region is not found"));
        region.setName(regionUpdateDto.getName());

        if (countryRepository.existsByNameIgnoreCase(regionUpdateDto.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The region name and the country name must not be the same");
        }

        region = regionRepository.save(region);

        return mapper.toDto(region);
    }

    public RegionDto delete(Integer regionId) {
        Region region = regionRepository
            .findById(regionId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region is not found"));

        regionRepository.delete(region);

        return mapper.toDto(region);
    }
}