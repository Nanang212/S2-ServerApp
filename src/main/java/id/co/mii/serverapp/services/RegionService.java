package id.co.mii.serverapp.services;

import java.util.List;

import javax.transaction.Transactional;

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

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region getById(Integer id) {
        return regionRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found !!!"));
    }

    @Transactional
    public Region create(Region region) {
        if (!regionRepository.existsByName(region.getName())) {
            return regionRepository.save(region);
        } else {
            throw new RuntimeException("Nama Region tidak boleh sama !!!.");
        }
    }

    @Transactional
    public Region update(Integer id, Region updatedRegion) {
        Region region = regionRepository.findById(id).orElse(null);
        if (region != null) {
            if (!regionRepository.existsByNameAndIdNot(updatedRegion.getName(), id)) {
                updatedRegion.setId(id);
                return regionRepository.save(updatedRegion);
            } else {
                throw new RuntimeException("Nama Region tidak boleh sama.");
            }
        } else {
            throw new RuntimeException("Region not found.");
        }
    }

    public Region delete(Integer id) {
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }

}
