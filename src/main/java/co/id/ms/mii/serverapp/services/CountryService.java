package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.Dtos.CountryRequest;
import co.id.ms.mii.serverapp.models.Country;
import co.id.ms.mii.serverapp.models.Region;
import co.id.ms.mii.serverapp.repositories.CountryRepository;
import co.id.ms.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

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
        Region setregion = regionRepository.findById(countryDto.getRegion_id()).orElseThrow(
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
    public Country update(CountryRequest countrydto, Integer id) {

        //find id
        Country country = countryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country ID Not Found!!!")
        );

        Region setregion = regionRepository.findById(countrydto.getRegion_id()).orElseThrow(
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
