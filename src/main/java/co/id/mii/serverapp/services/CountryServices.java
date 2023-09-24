package co.id.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import co.id.mii.serverapp.models.Country;
import co.id.mii.serverapp.models.Region;
import co.id.mii.serverapp.repositories.CountryRepository;
import co.id.mii.serverapp.repositories.RegionRepository;

@Service
public class CountryServices {
    private CountryRepository countryRepository;
    private RegionRepository regionRepository;


    public CountryServices(CountryRepository countryRepository, RegionRepository regionRepository) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
    }
    public List<Country> getAll(){
        return countryRepository.findAll();
    }
    public Country getAllById(Integer id){
        return countryRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Negara dengan - "+ id+" Tidak Ditemukan")
        );
    }

    @Transactional
    public Country insert(Country country){
        if (countryRepository.existsByName(country.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Nama Negara Sudah Ada!!");
        }
        List <Region> regions = regionRepository.findAll();

        for (Region region : regions){
            if (region.getName().equalsIgnoreCase(country.getName())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama Negara sudah ada di Benua, Coba dengan nama yang lain !!");

            }
        }
        return countryRepository.save(country);
    }

    public Country update ( Country country, Integer id){
        getAllById(id);
        country.setId(id);
        return countryRepository.save(country);
    }
    public Country delete(Integer id){
        Country country = getAllById(id);
        countryRepository.delete(country);
        return country;
    } 
}
