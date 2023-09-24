package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.models.Country;
import co.id.ms.mii.serverapp.models.Region;
import co.id.ms.mii.serverapp.repositories.CountryRepository;
import co.id.ms.mii.serverapp.repositories.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryService {
    private CountryRepository countryRepository;
    private RegionRepository regionRepository;

    public CountryService(CountryRepository countryRepository, RegionRepository regionRepository) {
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
    }

    public List<Country> getAll(){
        return countryRepository.findAll();
    }

    public Country GetAllById(Integer id){
        return countryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Country " + id + " Not Found")
        );
    }

    @Transactional
    public Country insert(Country country){

        if(countryRepository.existsByName(country.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Country Name already exists!!!");
        }

        List<Region> regions = regionRepository.findAll();

        for (Region region :
                regions) {
            if (region.getName().equalsIgnoreCase(country.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Country Name already exist in Region, use another one!!!");
            }
        }

        return countryRepository.save(country);
    }

    public Country update(Country country,Integer id){
        GetAllById(id);
        country.setId(id);
        return countryRepository.save(country);
    }

    public Country delete(Integer id){
        Country country = GetAllById(id);
        countryRepository.delete(country);
        return country;
    }
}
