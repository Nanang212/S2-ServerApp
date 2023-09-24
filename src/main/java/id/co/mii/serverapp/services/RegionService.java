package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepository;

@Service
public class RegionService {
    
    private final RegionRepository regionRepository;
    // private CountryRepository countryRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
        // this.countryRepository = countryRepository;
    }

    // getAll
    public List<Region> getAll(){
        return regionRepository.findAll();
    }

    public Region getById(Integer id){
        return regionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found!"));
    }

    public Region create(Region region){

        Region regionExist = regionRepository.findByName(region.getName());
        // Country countryExist = countryRepository.findByName(region.getName());

        if(regionExist != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region with the same name already exists!");
        }

        // if(countryExist != null){
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of the region is the same as the name of the country!");
        // }
        
        return regionRepository.save(region);
    }
    
    public Region update(Integer id, Region region){
       
        getById(id);
        region.setId(id);
       
        return regionRepository.save(region);
    }

    public Region delete(Integer id){
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }
}
