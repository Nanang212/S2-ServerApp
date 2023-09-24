package co.id.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.id.mii.serverapp.models.Region;
import co.id.mii.serverapp.repositories.RegionRepository;

@Service
public class RegionServices {
    private RegionRepository regionRepository;

    public RegionServices(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAll(){
        return regionRepository.findAll();
        }

    public Region getById(Integer Id){
        return regionRepository.findById(Id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data pada- " + Id + " heng ono")
        );
    }
    public Region create (Region region) {
        return regionRepository.save(region);
    }

    public Region update (Integer id,Region region){
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
