package co.id.ms.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.id.ms.mii.serverapp.models.Region;
import co.id.ms.mii.serverapp.repositories.RegionRepository;

@Service
public class RegionServices {
    private RegionRepository regionRepository;

    public RegionServices(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAll(){
        return regionRepository.findAll();
    }


    public Region getById(Integer id){
        return regionRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data ID: " + id + " Not Found")
        );
    }

    public Region create(Region region){
        if(regionRepository.existsByName(region.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Region Name Already Exists!!!");
        }

        return regionRepository.save(region);
    }

    public Region Update(Integer id, Region region){
        // find by id
        getById(id);
        // set id
        region.setId(id);
        // save
        return regionRepository.save(region);
    }

    public Region delete(Integer id){
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }
}
