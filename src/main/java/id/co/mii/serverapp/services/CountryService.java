package id.co.mii.serverapp.services;

import java.math.BigInteger;
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

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country GetById(Integer id) {
        return countryRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak ditemukan id no : " + id));
    }

    // without dto
    public Country insert(Country country) {
        BigInteger count = countryRepository.countByName(country.getName());
        if (count.intValue() > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah ada !!! ");
        }

        List<Region> regions = regionRepository.findAll();

        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(country.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah ada di wilayah !!!");
            }
        }
        return countryRepository.save(country);
    }

    // with dto
    public Country insertDTO(CountryRequest countryRequest) {
        Country country = new Country();
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        Region region = regionService.getById(countryRequest.getRegionId());
        country.setRegion(region);

        return countryRepository.save(country);
    }

    // with dto by modelMapper
    public Country insertDTOByModelMapper(CountryRequest countryRequest) {
        Country country = modelMapper.map(countryRequest, Country.class);
        country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepository.save(country);
    }

    public Country update(Integer id, Country country) {
        GetById(id);
        country.setId(id);

        BigInteger count = countryRepository.countByName(country.getName());
        if (count.intValue() > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah ada !!! ");
        }

        List<Region> regions = regionRepository.findAll();

        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(country.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah ada di wilayah !!!");
            }
        }

        return countryRepository.save(country);
    }

    public Country delete(Integer id) {
        Country country = GetById(id);
        countryRepository.delete(country);
        return country;
    }

    // custom manual
    public Map<String, Object> getByIdMap(Integer id) {
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

    public List<Map<String, Object>> getAllCustomStream() {
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
