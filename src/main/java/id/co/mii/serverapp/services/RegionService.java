package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepositori;

@Service
public class RegionService {
    private RegionRepositori regionRepository;

    public RegionService(RegionRepositori regionRepositori) {
        regionRepository = regionRepositori;
    }

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region findById(Integer id) {
        return regionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region Not Found in id : " + id));
    }

    public Region insertRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region update(Integer id, Region region) {
        findById(id);
        region.setId(id);
        return regionRepository.save(region);
    }

    public Region delete(Integer id) {
        Region region = findById(id);
        regionRepository.delete(region);
        return region;
    }

}
