package id.co.mii.serverapp.services;

import java.util.List;
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

    // getAll
    public List<Region> getAll(){
        return regionRepository.findAll();
    }

    public Region getById(Integer id){
        return regionRepository.findById(id).orElseThrow(() -> new ResponseStatusException
        (HttpStatus.NOT_FOUND, "Region not found!"));
    }

    public Region create(Region region){
        if(regionRepository.findByName(region.getName()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region names already exist!");
        }
        if(countryRepository.findByName(region.getName()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The Region name cannot be the same as the Country name!");
        }
        if(region.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name cannot be empty!");
        }
        return regionRepository.save(region);
    }

    public Region update(Integer id, Region region){
        getById(id); // findId
        region.setId(id); // set data
        if(regionRepository.findByName(region.getName()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region names already exist!");
        }
        if(countryRepository.findByName(region.getName()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The Region name cannot be the same as the Country name!");
        }
        if(region.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name cannot be empty!");
        }
        return regionRepository.save(region); // save
    }

    public Region delete(Integer id){
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }
}
