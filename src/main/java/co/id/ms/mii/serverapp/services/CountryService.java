package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.dto.request.CountryRequest;
import co.id.ms.mii.serverapp.models.Country;
import co.id.ms.mii.serverapp.models.Region;
import co.id.ms.mii.serverapp.repositories.CountryRepository;
import co.id.ms.mii.serverapp.repositories.RegionRepository;
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
public class CountryService {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private ModelMapper modelMapper;
    /*
     * 1. Autowired on properties
     * 2. Autowired on setter
     * 3. Autowired on constructor
     */

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country GetById(Integer id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Country " + id + " Not Found")
        );
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


    // without dto
    public Country create(Country country) {
        if (countryRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Name Country is already exists!!!"
            );
        }

        if (regionRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Name Region is already exists!!!"
            );
        }

        return countryRepository.save(country);
    }

    public Country insert(CountryRequest countryDto) {

        if (!countryRepository.findByNameOrRegionName(countryDto.getName(), countryDto.getName()).isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name is already exists!!!");
        }

        if (countryRepository.existsByName(countryDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country Name already exists!!!");
        }

        //get all region
        List<Region> regions = regionRepository.findAll();

        //check jika country name ada yang sama, dengan semua data region name
        for (Region region :
                regions) {
            if (region.getName().equalsIgnoreCase(countryDto.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Country Name already exist in Region, use another one!!!");
            }
        }

        // checking jika ada region yang ada dengan parameter region_id di countrydto
        Region setregion = regionRepository.findById(countryDto.getRegionId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region ID is not Found")
        );

        Country country = new Country();
        country.setCode(countryDto.getCode());
        country.setName(countryDto.getName());
        country.setRegion(setregion);

        return countryRepository.save(country);
    }

    //    public Country update(Country country,Integer id){
//        if(countryRepository.existsByName(country.getName())){
//            throw new ResponseStatusException(HttpStatus.CONFLICT,"Country Name already exists!!!");
//        }
//
//        GetAllById(id);
//        country.setId(id);
//        return countryRepository.save(country);
//    }

    public Country update(Integer id, Country country) {
        GetById(id);
        country.setId(id);
        return countryRepository.save(country);
    }

    public Country updateDto(CountryRequest countrydto, Integer id) {

        //find id
        Country country = countryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country ID Not Found!!!")
        );

        Region setregion = regionRepository.findById(countrydto.getRegionId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region ID is not Found")
        );

        //check name with query method
        if (countryRepository.existsByName(countrydto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country Name already exists!!!");
        }

        country.setCode(countrydto.getCode());
        country.setName(countrydto.getName());
        country.setRegion(setregion);
        return countryRepository.save(country);
    }

    public Country delete(Integer id) {
        Country country = GetById(id);
        countryRepository.delete(country);
        return country;
    }
}
