package id.co.mii.serverapp.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepository;
import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
    }

    public Country createCountry(Country country) {
        if(countryRepository.findByName(country.getName()) !=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name already exists!");
        }
        if(countryRepository.isExistsByName(country.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The Country name cannot be the same as the Region name!");
        }
        if(country.getCode().length()!= 2){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country code must be 2 letters!");
        }
        if(countryRepository.findByCode(country.getCode()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country Code already exists!");
        }
        return countryRepository.save(country);
    }

    public Country updateCountry(Integer id, Country updatedCountry) {
        getCountryById(id);
        if(countryRepository.findByName(updatedCountry.getName()) !=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name already exists!");
        }
        if(countryRepository.isExistsByName(updatedCountry.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The Country name cannot be the same as the Region name!");
        }
        if(updatedCountry.getCode().length()!= 2){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country code must be 2 letters!");
        }
        if(countryRepository.findByCode(updatedCountry.getCode()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country Code already exists!");
        }
        updatedCountry.setId(id);
        
        return countryRepository.save(updatedCountry);
    }

    public Country deleteCountry(Integer id) {
        Country country = getCountryById(id);
        countryRepository.delete(country);
        return country;
    }
}
