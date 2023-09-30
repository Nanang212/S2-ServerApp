package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepo;
import id.co.mii.serverapp.repositories.RegionRepo;
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.services.base.BaseService;
import id.co.mii.serverapp.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CountryService extends BaseService<Country> {
    private CountryRepo countryRepo;
    private RegionRepo regionRepo;
    private RegionService regionService;
    private ModelMapper modelMapper;
    @Override
    public Country create(Country country) {
        if (regionRepo.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already belong to region");
        }
        if (countryRepo.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already used");
        }
        if (countryRepo.existsByCode(country.getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country code is already used");
        }
        return countryRepo.save(country);
    }

    public Country create(CountryRequest countryRequest) {
        if (regionRepo.existsByName(countryRequest.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already belong to region");
        }
        if (countryRepo.existsByName(countryRequest.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already used");
        }
        if (countryRepo.existsByCode(countryRequest.getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country code is already used");
        }
        Country country = modelMapper.map(countryRequest, Country.class);
        country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepo.save(country);
    }

    @Override
    public Country update(Integer id, Country country) {
        Country updatedCountry = getById(id);
        if (!StringUtils.isEmptyOrNull(country.getName())
                && !updatedCountry.getName().equalsIgnoreCase(country.getName())) {
            if (countryRepo.existsByName(country.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already used");
            }
            updatedCountry.setName(country.getName());
        }
        if (!StringUtils.isEmptyOrNull(country.getCode())
                && !updatedCountry.getCode().equalsIgnoreCase(country.getCode())) {
            if (countryRepo.existsByCode(country.getCode())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Country code is already used");
            }
            updatedCountry.setCode(country.getCode());
        }
        if (country.getRegion() != null) {
            updatedCountry.setRegion(country.getRegion());
        }
        return countryRepo.save(updatedCountry);
    }

    public List<Country> getAll(String keyword) {
        if (keyword != null) {
            return countryRepo.findALlBy(keyword);
        }
        return countryRepo.findAll();
    }

    public Map<String, Object> getByIdCustom(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Country country = countryRepo.findById(id).get();

        result.put("countryId", country.getId());
        result.put("countryCode", country.getCode());
        result.put("countryName", country.getName());
        result.put("regionId", country.getRegion().getId());
        result.put("regionName", country.getRegion().getName());

        return result;
    }

    public List<Map<String, Object>> getAllCustom() {
        List<Map<String, Object>> results = new ArrayList<>();
        List<Country> countries = countryRepo.findAll();

        for (Country country : countries) {
            Map<String, Object> result = new HashMap<>();
            result.put("countryId", country.getId());
            result.put("countryCode", country.getCode());
            result.put("countryName", country.getName());
            result.put("regionId", country.getRegion().getId());
            result.put("regionName", country.getRegion().getName());
            results.add(result);
        }
        return results;
    }

    public List<Map<String, Object>> getAllCustomStream() {
        return countryRepo
                .findAll()
                .stream()
                .map(country -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("countryId", country.getId());
                    result.put("countryCode", country.getCode());
                    result.put("countryName", country.getName());
                    result.put("regionId", country.getRegion().getId());
                    result.put("regionName", country.getRegion().getName());
                    return result;
                })
                .collect(Collectors.toList());
    }
}
