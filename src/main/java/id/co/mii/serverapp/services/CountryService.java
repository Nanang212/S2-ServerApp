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
    private RegionRepositori regionRepositori;
    private CountryRepository countryRepository;

    public List<Country> getAll(){
        return countryRepository.findAll();
    }

    public Country getByIdCountry(Integer id){
        return countryRepository
        .findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak ditemukan id"));
    }

    public Country insert(Country country){
        if(countryRepository.existsByName(country.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah digunakan");
        }

        List<Region> regions = regionRepositori.findAll();
        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(country.getName())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama negara sudah digunakan");
            }
        }
        return countryRepository.save(country);
        

        
    }
    
}
