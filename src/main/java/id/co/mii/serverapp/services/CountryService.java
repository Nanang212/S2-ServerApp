package id.co.mii.serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
   

  private CountryRepository  countryRepo;
  private RegionRepository regionRepository;
    
    // public CountryService(CountryRepository countryRepo) {
    //     this.countryRepo = countryRepo;
    // }

    public CountryService(CountryRepository countryRepo, RegionRepository regionRepository) {
        this.countryRepo = countryRepo;
        this.regionRepository = regionRepository;
    }

     public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    public Optional<Country> getCountryById(Integer id) {
        return countryRepo.findById(id);
    }

    public Country createCountry(Country country) {
        if (regionRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Failed not found");
            //country tidak boleh sama dengan region      
        }
      if (countryRepo.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Failed not found");
            //country tidak boleh sama dengan region      
        }
        return countryRepo.save(country);



    }

    public Country updateCountry(Integer id, Country updatedCountry) {
        Optional<Country> existingCountry = countryRepo.findById(id);
        if (existingCountry.isPresent()) {
            Country country = existingCountry.get();
            country.setCode(updatedCountry.getCode());
            country.setName(updatedCountry.getName());
            return countryRepo.save(country);
        }
        return null;
    }

    public void deleteCountry(Integer id) {
        countryRepo.deleteById(id);
    }
}
