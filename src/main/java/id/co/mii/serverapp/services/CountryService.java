package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepository;


@Service
public class CountryService {
    
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAll(){
        return countryRepository.findAll();
    }

    public Country getById(Integer id){
        return countryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak ada id :" + id));
    }

    public Country create(Country country){
        if(countryRepository.existsByName(country.getName())){
            throw  new ResponseStatusException(HttpStatus.CONFLICT,"name already exist");
        } 

        return countryRepository.save(country);
    }

  
    public Country update(Country country, Integer id){
        getById(id);
        return create(country);
    }

    public Country delete(Integer id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }

}
