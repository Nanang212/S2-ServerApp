package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegionService {
    private final RegionRepo regionRepo;

    public RegionService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    public List<Region> getAll() {
        return regionRepo.findAll();
    }

    public Region getById(Integer id) {
        return regionRepo
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found"));
    }

    public Region create(Region region) {
        return regionRepo.save(region);
    }

    public Region update(Integer id, Region region) {
        getById(id);
        region.setId(id);
        return regionRepo.save(region);
    }

    public Region delete(Integer id) {
        Region region = getById(id);
        regionRepo.delete(region);
        return region;
    }
}
