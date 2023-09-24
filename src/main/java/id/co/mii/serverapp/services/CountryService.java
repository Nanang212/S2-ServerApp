package id.co.mii.serverapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;

@Service
public class CountryService {
    private RegionRepository regionRepository;
    private CountryRepository countryRepository;

        
    public CountryService(RegionRepository regionRepository, CountryRepository countryRepository) {
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
    }


    public List<Country> getAll(){
        return countryRepository.findAll();
    }


    public Country getById(Integer id){
        return countryRepository.findById(id).orElseThrow(() -> new ResponseStatusException
        (HttpStatus.NOT_FOUND, "Country not found!"));
    }

   
    // create
    @Transactional 
    public Country create(Country country){
    
        if (countryRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name already exists!");
        }
        
        if (regionRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name cannot be the same as the region name");
            // throw new IllegalArgumentException("Region name cannot be the same as the country name");
        }
        
        return countryRepository.save(country);
    }

    @Transactional 
    public Country update(Integer id,Country country){
        // find id
        getById(id);
         // set data
        country.setId(id);

        if (regionRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name cannot be the same as the region name");
            // throw new IllegalArgumentException("Region name cannot be the same as the country name");
        }
        
        return countryRepository.save(country);     
    }


    public Country delete(Integer id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }

}


