package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.request.CountryRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final Validator validator;

    public Country create(CountryRequest request) {
        Set<ConstraintViolation<CountryRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (countryRepository.existsByCodeIgnoreCase(request.getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country code already exists. Use another code");
        }

        if (countryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name already exists. Use another name");
        }

        Region region = regionRepository
            .findById(request.getRegionId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region not found"));

        if (request.getName().equalsIgnoreCase(region.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The region name and the country name must not be the same");
        }

        Country country = new Country();
        country.setRegion(region);
        country.setCode(request.getCode().toUpperCase());
        country.setName(request.getName());

        return countryRepository.save(country);
    }

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getById(Integer countryId) {

        return countryRepository
            .findById(countryId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
    }

    public Country update(Integer countryId, CountryRequest request) {
        Set<ConstraintViolation<CountryRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        Country country = countryRepository
            .findById(countryId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
        Region region = regionRepository
            .findById(request.getRegionId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region not found"));

        if (request.getName().equalsIgnoreCase(region.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The region name and the country name must not be the same");
        }

        country.setRegion(region);
        country.setCode(request.getCode());
        country.setName(request.getName());

        return countryRepository.save(country);
    }

    public Country delete(Integer countryId) {
        Country country = countryRepository
            .findById(countryId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        countryRepository.delete(country);

        return country;
    }
}
