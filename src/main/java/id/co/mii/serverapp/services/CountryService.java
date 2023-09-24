package id.co.mii.serverapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
        return countryRepository.findById(id).get();
    }

    @Transactional
    public Country create(Country country){
        return countryRepository.save(country);
    }

    @Transactional
    public Country update(Country country, Integer id){
        getById(id);
        country.setId(id);
        return countryRepository.save(country);
    }

    public Country delete(Integer id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }

}
