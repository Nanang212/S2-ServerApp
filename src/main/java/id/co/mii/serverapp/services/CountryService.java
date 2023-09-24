package id.co.mii.serverapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.CountryRepository;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found !!!"));
    }

    @Transactional
    public Country create(Country country) {
        String countryName = country.getName();
        Region region = country.getRegion();

        if (region != null && !countryName.equalsIgnoreCase(region.getName())) {
            if (!countryRepository.existsByName(countryName)) {
                return countryRepository.save(country);
            } else {
                throw new RuntimeException("Nama Country tidak boleh sama dengan nama Region.");
            }
        } else {
            throw new RuntimeException("Region harus diatur, dan nama Country tidak boleh sama dengan nama Region.");
        }
    }

    @Transactional
    public Country update(Integer id, Country updatedCountry) {
        Country existingCountry = countryRepository.findById(id).orElse(null);

        if (existingCountry != null) {
            String newCountryName = updatedCountry.getName();
            Region region = updatedCountry.getRegion();

            // Periksa apakah region telah diatur dan nama Country tidak sama dengan nama
            // Region
            if (region != null && !newCountryName.equalsIgnoreCase(region.getName())) {
                // Periksa apakah nama Country yang akan diupdate tidak sama dengan nama Country
                // lain
                if (!countryRepository.existsByNameAndIdNot(newCountryName, id)) {
                    updatedCountry.setId(id);
                    return countryRepository.save(updatedCountry);
                } else {
                    throw new RuntimeException("Nama Country tidak boleh sama dengan nama Country lain.");
                }
            } else {
                throw new RuntimeException(
                        "Region harus diatur, dan nama Country tidak boleh sama dengan nama Region.");
            }
        } else {
            throw new RuntimeException("Country not found.");
        }
    }

    public Country delete(Integer id) {
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }

}
