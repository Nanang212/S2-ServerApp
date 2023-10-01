package id.co.mii.serverapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.request.CountryRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;
    private RegionRepository regionRepository;
    private RegionService regionService;
    private ModelMapper modelMapper;

    // public CountryService(CountryRepository countryRepository, RegionRepository
    // regionRepository) {
    // this.countryRepository = countryRepository;
    // this.regionRepository = regionRepository;
    // }

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found!"));
    }

    // without dto
    public Country create(Country country) {
        if (regionRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name tidak boleh sama dengan nama region!");
        }
        if (countryRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already used!");
        }
        return countryRepository.save(country);
    }

    // with dto
    public Country createDTO(CountryRequest countryRequest) {
        Country country = new Country();
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        Region region = regionService.getById(countryRequest.getRegionId());
        country.setRegion(region);

        return countryRepository.save(country);
    }

    // with dto model mapper
    public Country createDTOByModelMapper(CountryRequest countryRequest) {
        Country country = modelMapper.map(countryRequest, Country.class);
        country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepository.save(country);
    }

    public Country update(Integer id, Country country) {
        getById(id);
        country.setId(id);
        if (regionRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name tidak boleh sama dengan nama region!");
        }
        if (countryRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already used!");
        }
        return countryRepository.save(country);
    }

    public Country delete(Integer id) {
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }

    // custom manual
    public Map<String, Object> getByIdCustom(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Country country = countryRepository.findById(id).get();

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

    public List<Map<String, Object>> getAllCustomStream(){
        return countryRepository
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
