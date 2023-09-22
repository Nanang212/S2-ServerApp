package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepository;

@Service
public class RegionService {
    private RegionRepository regionRepository;


    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getdAll(){
        return regionRepository.findAll();
    }

    public Region getById(Integer id){
        return regionRepository.findById(id).orElseThrow(() -> new ResponseStatusException
        (HttpStatus.NOT_FOUND, "Region not found !!!"));
    }

    public Region create(Region region){
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
