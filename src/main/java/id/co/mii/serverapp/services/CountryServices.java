package id.co.mii.serverapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.CountryRequest;
import id.co.mii.serverapp.repositories.CountryRepositories;
import id.co.mii.serverapp.repositories.RegionRepositories;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;


@Service
@AllArgsConstructor
public class CountryServices {
    private CountryRepositories countryRepositories;
    private RegionRepositories regionRepositories;
    private RegionServices regionServices;
    private ModelMapper modelMapper;
    // private RegionServices regionServices;

    public List<Country> getAll() {
        return countryRepositories.findAll();
    }

    public Country getById(Integer id) {
        return countryRepositories
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Country not found!!!"));
    }

    // without dto
    public Country create(Country country) {
        if (countryRepositories.existsByName(country.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Name Country is already exists!!!");
        }

        if (regionRepositories.findByName(country.getName()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Name Region is already exists!!!");
        }

        return countryRepositories.save(country);
    }

    // with dto
    public Country createDTO(CountryRequest countryRequest) {
        Country country = new Country();
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        Region region = regionServices.getById(countryRequest.getRegionId());
        country.setRegion(region);

        return countryRepositories.save(country);
    }
    // with dto by model mapper
    public Country createDTOByModelMapper(CountryRequest countryRequest) {
        Country country = modelMapper.map(countryRequest, Country.class);
        country.setRegion(regionServices.getById(countryRequest.getRegionId()));
        return countryRepositories.save(country);
      }
    
      public Country update(Integer id, Country country) {
        getById(id);
        country.setId(id);
        return countryRepositories.save(country);
      }
    
      public Country delete(Integer id) {
        Country country = getById(id);
        countryRepositories.delete(country);
        return country;
      }
    // custom manual
    public Map<String, Object> getByIdCustom(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Country country = countryRepositories.findById(id).get();

        result.put("countryId", country.getId());
        result.put("countryCode", country.getCode());
        result.put("countryName", country.getName());
        result.put("regionId", country.getRegion().getId());
        result.put("regionName", country.getRegion().getName());

        return result;
    }

    public List<Map<String, Object>> getAllCustom() {
        List<Map<String, Object>> results = new ArrayList<>();
        List<Country> countries = countryRepositories.findAll();

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
        return countryRepositories
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
