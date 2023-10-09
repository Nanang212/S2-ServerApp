package id.co.mii.serverapp.services;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegionService {
    
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;
    
    public List<Region> getAll(){
        return regionRepository.findAll();
    }

    public Region getById(Integer id){
        return regionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found!"));
    }

    public Region create(Region region){

        Optional<Region> regionExist = regionRepository.findByName(region.getName());
        Country countryExist = countryRepository.findByName(region.getName());

        if(regionExist.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region with the same name already exists!");
        }

        if(countryExist != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of the region is the same as the name of the country!");
        }

        if(region.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Name field is required!");
        }

        return regionRepository.save(region);
    }
    
    public Region update(Integer id, Region region){
       
        getById(id);
        region.setId(id);
        
        Optional<Region> regionExist = regionRepository.findByName(region.getName());
        Country countryExist = countryRepository.findByName(region.getName());

        if(regionExist.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Region with the same name already exists!");
        }

        if(countryExist != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of the region is the same as the name of the country!");
        }

        if(region.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Name field is required!");
        }
        
        return regionRepository.save(region);
    }

    public Region delete(Integer id){
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }

    // Native
    public List<Region> searchAllNameNative(String name) {
        return regionRepository.searchAllNameNative("%" + name + "%");
    }

    // JPQL
    public List<Region> searchAllNameJPQL(String name) {
        return regionRepository.searchAllNameJPQL("%" + name + "%");
    }

    public List<String> getAllName() {
        return regionRepository.getAllName();
    }
}
