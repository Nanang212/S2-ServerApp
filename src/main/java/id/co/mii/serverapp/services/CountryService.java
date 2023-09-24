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
        validateCountryNameNotSameAsRegionName(country.getRegion().getName(), country.getName());
        return countryRepository.save(country);
    }

    public Country updateCountry(Integer id, Country updatedCountry) {
        getCountryById(id);
        updatedCountry.setId(id);
        validateCountryNameNotSameAsRegionName(updatedCountry.getRegion().getName(), updatedCountry.getName());
        return countryRepository.save(updatedCountry);
    }

    public Country deleteCountry(Integer id) {
        Country country = getCountryById(id);
        countryRepository.delete(country);
        return country;
    }

    private void validateCountryNameNotSameAsRegionName(String regionName, String countryName) {
        if (regionName.equalsIgnoreCase(countryName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country name cannot be the same as the region.");
        }
    }
}
