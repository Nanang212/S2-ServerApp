package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final ModelMapper modelMapper;
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

        Country country = modelMapper.map(request, Country.class);
        country.setRegion(region);

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

    // custom manual
    public Map<String, Object> getByIdCustom(Integer countryId) {
        Map<String, Object> result = new HashMap<>();
        Country country = getById(countryId);

        result.put("countryId", country.getId());
        result.put("countryCode", country.getCode());
        result.put("countryName", country.getName());
        result.put("regionId", country.getRegion().getId());
        result.put("regionName", country.getRegion().getName());

        return result;
    }

    public List<Map<String, Object>> getAllCustom() {
        List<Map<String, Object>> results = new ArrayList<>();
        List<Country> countries = countryRepository.findAll();

        for (Country country : countries) {
            Map<String, Object> result = new HashMap<String, Object>() {{
                put("countryId", country.getId());
                put("countryCode", country.getCode());
                put("countryName", country.getName());
                put("regionId", country.getRegion().getId());
                put("regionName", country.getRegion().getName());
            }};

            results.add(result);
        }
        return results;
    }

    public List<Map<String, Object>> getAllCustomStream() {
        return countryRepository
            .findAll()
            .stream()
            .map(country -> new HashMap<String, Object>() {{
                put("countryId", country.getId());
                put("countryCode", country.getCode());
                put("countryName", country.getName());
                put("regionId", country.getRegion().getId());
                put("regionName", country.getRegion().getName());
            }})
            .collect(Collectors.toList());
    }
}
