package id.co.mii.serverapp.services;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.dto.CreateRegionDto;
import id.co.mii.serverapp.dto.UpdateRegionDTO;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepository;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getById(int Id) {
        return regionRepository.findById(Id).get();
    }

    public void deleteRegion(int id) {
        regionRepository.deleteById(id);
    }

    public Region updateRegion(int id, UpdateRegionDTO dto) {
        Region region = regionRepository.findById(id).orElse(null);
        if (region != null) {
            region.setName(dto.getName());
            regionRepository.save(region);
        } else {
            throw new IllegalArgumentException("Nama region tidak boleh kosong");
        }
        return region;
    }

    public Region createRegion(CreateRegionDto dto) {
        Region region = new Region();
        region.setName(dto.getName());
        return regionRepository.save(region);
    }
}
