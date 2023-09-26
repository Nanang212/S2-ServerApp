package id.co.mii.serverapp.services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
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

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country GetById(Integer id) {
        return countryRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak ditemukan id no : " + id));
    }

    public Country insert(Country country) {
        BigInteger count = countryRepository.countByName(country.getName());
        if (count.intValue() > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah ada !!! ");
        }

        List<Region> regions = regionRepository.findAll();

        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(country.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah ada di wilayah !!!");
            }
        }
        return countryRepository.save(country);
    }

    public Country update(Integer id, Country country) {
        GetById(id);
        country.setId(id);

        BigInteger count = countryRepository.countByName(country.getName());
        if (count.intValue() > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah ada !!! ");
        }

        List<Region> regions = regionRepository.findAll();

        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(country.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah ada di wilayah !!!");
            }
        }

        return countryRepository.save(country);
    }

    public Country delete(Integer id) {
        Country country = GetById(id);
        countryRepository.delete(country);
        return country;
    }
}
