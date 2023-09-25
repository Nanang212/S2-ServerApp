package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepositori;

@Service
public class CountryService {
    private RegionRepositori regionRepository;
    private CountryRepository countryRepository;

    public CountryService(RegionRepositori regionRepository, CountryRepository countryRepository) {
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
    }

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getByIdCountry(Integer id) {
        return countryRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak ditemukan id"));
    }

    public Country insert(Country country) {
        if (countryRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah digunakan");
        }

        List<Region> regions = regionRepository.findAll();
        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(country.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah digunakan");
            }
        }
        return countryRepository.save(country);
    }

    public Country update(Integer id, Country country) {

        Country existCountry = getByIdCountry(id);

        if (countryRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah digunakan");
        }

        List<Region> regions = regionRepository.findAll();

        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(existCountry.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah digunakan");
            }

        }
        existCountry.setCode(country.getCode());
        existCountry.setName(country.getName());
        existCountry.setRegion(country.getRegion());

        return countryRepository.save(existCountry);

    }

    public Country delete(Integer id) {
        Country country = getByIdCountry(id);
        countryRepository.delete(country);
        return country;
    }

}
