package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;

@Service
public class CountryService {
    private CountryRepository countryRepository;
    private RegionRepository regionRepository;
    
    public CountryService(CountryRepository countryRepository, RegionRepository regionRepository) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
    }
    public List<Country> getAll(){
        return countryRepository.findAll();
    }
    public Country getById(Integer id){
        return countryRepository.findById(id).orElseThrow(() -> new ResponseStatusException
            (HttpStatus.NOT_FOUND, "Country not found!"));
    }
    public Country create(Country country){
        if (regionRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name tidak boleh sama dengan nama region!");
        }
        if (countryRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already used!");
        }      
        return countryRepository.save(country);
    }
    public Country update(Integer id, Country country){
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
    public Country delete(Integer id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }
}
