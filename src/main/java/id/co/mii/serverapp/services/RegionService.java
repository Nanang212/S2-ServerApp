package id.co.mii.serverapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;

@Service
public class RegionService {
    private RegionRepository regionRepository;
    private CountryRepository countryRepository;

    
    public RegionService(RegionRepository regionRepository, CountryRepository countryRepository) {
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
    }


    public List<Region> getAll(){
        return regionRepository.findAll();
    }


    public Region getById(Integer id){
        return regionRepository.findById(id).orElseThrow(() -> new ResponseStatusException
        (HttpStatus.NOT_FOUND, "Region not found!"));
    }



    // create
    @Transactional 
    public Region create(Region region){

        // checkName(region);
        if (regionRepository.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name already exists!");
        }
        
        if (countryRepository.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name cannot be the same as the country name");
            // throw new IllegalArgumentException("Region name cannot be the same as the country name");
        }
        
        return regionRepository.save(region);
    }
    @Transactional
    public Region update(Integer id,Region region){
        // find id
        getById(id);
         // set data
        region.setId(id);
        if (countryRepository.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name cannot be the same as the country name");
            // throw new IllegalArgumentException("Region name cannot be the same as the country name");
        }
        return regionRepository.save(region);        
    }


    public Region delete(Integer id){
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }


}
