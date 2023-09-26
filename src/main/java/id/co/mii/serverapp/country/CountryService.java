package id.co.mii.serverapp.country;

import id.co.mii.serverapp.country.dto.CountryCreationDto;
import id.co.mii.serverapp.country.dto.CountryDto;
import id.co.mii.serverapp.country.dto.CountryUpdateDto;
import id.co.mii.serverapp.region.Region;
import id.co.mii.serverapp.region.RegionRepository;
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
public class CountryService {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final Validator validator;
    private final Mapper mapper;

    public CountryService(CountryRepository countryRepository, RegionRepository regionRepository, Validator validator, Mapper mapper) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    public CountryDto create(CountryCreationDto countryCreationDto) {
        Set<ConstraintViolation<CountryCreationDto>> constraintViolations = validator.validate(countryCreationDto);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (countryRepository.existsByCodeIgnoreCase(countryCreationDto.getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country code already exists. Use another code");
        }

        if (countryRepository.existsByNameIgnoreCase(countryCreationDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name already exists. Use another name");
        }

        Region region = regionRepository
            .findById(countryCreationDto.getRegionId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region not found"));

        if (countryCreationDto.getName().equalsIgnoreCase(region.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The region name and the country name must not be the same");
        }

        Country country = new Country();
        country.setRegion(region);
        country.setCode(countryCreationDto.getCode().toUpperCase());
        country.setName(countryCreationDto.getName());
        country = countryRepository.save(country);

        return mapper.toDto(country);
    }

    public List<CountryDto> getAll() {
        return countryRepository
            .findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    public CountryDto getById(Integer countryId) {
        Country country = countryRepository
            .findById(countryId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        return mapper.toDto(country);
    }

    public CountryDto update(Integer countryId, CountryUpdateDto countryUpdateDto) {
        Set<ConstraintViolation<CountryUpdateDto>> constraintViolations = validator.validate(countryUpdateDto);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        Country country = countryRepository
            .findById(countryId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
        Region region = regionRepository
            .findById(countryUpdateDto.getRegionId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region not found"));

        if (countryUpdateDto.getName().equalsIgnoreCase(region.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The region name and the country name must not be the same");
        }

        country.setRegion(region);
        country.setCode(countryUpdateDto.getCode());
        country.setName(countryUpdateDto.getName());

        country = countryRepository.save(country);

        return mapper.toDto(country);
    }

    public CountryDto delete(Integer countryId) {
        Country country = countryRepository
            .findById(countryId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        countryRepository.delete(country);

        return mapper.toDto(country);
    }
}
