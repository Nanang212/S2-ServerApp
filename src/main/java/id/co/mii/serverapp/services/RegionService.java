package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepositori;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegionService {
    private RegionRepositori regionRepository;

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region findById(Integer id) {
        return regionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region Not Found in id : " + id));
    }

    public Region insertRegion(Region region) {
        if (regionRepository.existsByName(region.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama wilayah sudah digunakan");
        }

        if(region.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nama wilayah harus diisi");
        }
        return regionRepository.save(region);
    }

    public Region update(Integer id, Region region) {
        if (regionRepository.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama wilayah sudah digunakan");
        }
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
