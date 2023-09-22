package id.co.mii.serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
   

  private CountryRepository countryRepo;
    
    public CountryService() {

}


    public CountryService(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }


    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    public Optional<Country> getCountryById(Integer id) {
        return countryRepo.findById(id);
    }

    public Country createCountry(Country country) {
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
