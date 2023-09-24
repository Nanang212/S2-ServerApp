package id.co.mii.serverapp.services;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionsRepository;

@Service
public class RegionService {
    
    private RegionsRepository regionsRepository;

    public RegionService(RegionsRepository regionsRepository) {
        this.regionsRepository = regionsRepository;
    }

    public List<Region> getAll(){
        return regionsRepository.findAll();
    }


    public Region getById(Integer id){
        return regionsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak ada id: " + id));
    }

    public Region create(Region region){
        return regionsRepository.save(region);
    }

    public Region update(Integer id, Region region){
        // find id
        getById(id);
        // set data idnya
        region.setId(id);
        //save
        return regionsRepository.save(region);
    }

    public Region delete(Integer id){
        Region region = getById(id);
        // find id
        regionsRepository.delete(region);
        return region;
    }
    

}
