package co.id.mii.serverapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import co.id.mii.serverapp.models.Country;
import co.id.mii.serverapp.models.Region;
import co.id.mii.serverapp.models.dto.request.CountryRequest;
import co.id.mii.serverapp.repositories.CountryRepository;
import co.id.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;

//validasi

@Service
@AllArgsConstructor
public class CountryServices {
    private CountryRepository countryRepository;
    private RegionRepository regionRepository;
    private RegionServices regionService;
    private ModelMapper modelMapper;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getAllById(Integer id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Data Negara dengan - " + id + " Tidak Ditemukan"));
    }

    // tanpa dto
    public Country create(Country country) {
        if (!countryRepository
                .findByNameOrRegionName(country.getName(), country.getName())
                .isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Name is already exists!!!");
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

    // with dto by model mapper
    public Country createDTOByModelMapper(CountryRequest countryRequest) {
        Country country = modelMapper.map(countryRequest, Country.class);
        country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepository.save(country);
    }

    @Transactional
    public Country insert(Country country) {
        if (countryRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama Negara Sudah Ada!!");
        }

        List<Region> regions = regionRepository.findAll();
        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(country.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Nama Negara sudah ada di Benua, Coba dengan nama yang lain !!");

            }
        }
        return countryRepository.save(country);
    }

    public Country update(Country country, Integer id) {
        getAllById(id);
        country.setId(id);
        return countryRepository.save(country);
    }

    public Country delete(Integer id) {
        Country country = getAllById(id);
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
