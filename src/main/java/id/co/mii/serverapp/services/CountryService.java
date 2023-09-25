package id.co.mii.serverapp.services;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;

@Service
public class CountryService {
    
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

    public CountryService(CountryRepository countryRepository, RegionRepository regionRepository){
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
    }

    public List<Country> getAll(){
        return countryRepository.findAll();
    }

    public Country getById(Integer id){
        return countryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found!"));
    }

    public Country create(Country country){

        Region regionExist = regionRepository.findByName(country.getName());
        Country countryExist = countryRepository.findByName(country.getName());

        if(regionExist != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country with the same name already exists!");
        }

        if(countryExist != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of the country is the same as the name of the region!");
        }

        if (country.getCode().length() != 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Length of code field must be 2 characters");
        }

        if (countryRepository.findByCode(country.getCode()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Code of the country has already exists!");
        }

        if (country.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name field required!");
        }

        return countryRepository.save(country);
    }
    
    public Country update(Integer id, Country country){
       
        getById(id);
        country.setId(id);
        
        Region regionExist = regionRepository.findByName(country.getName());
        Country countryExist = countryRepository.findByName(country.getName());

        if(countryExist != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country with the same name already exists!");
        }

        if(regionExist != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of the country is the same as the name of the region!");
        }

        if (country.getCode().length() != 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Length of code field must be 2 characters");
        }

        if (countryRepository.findByCode(country.getCode()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Code of the country has already exists!");
        }

        if (country.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name field required!");
        }

        return countryRepository.save(country);
    }

    public Country delete(Integer id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }
}
