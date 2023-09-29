package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegionService {

    private RegionRepository regionRepository;

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region getById(Integer id) {
        return regionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak ditemukan id no :" + id));
    }

    public Region insertData(Region region) {
         if (regionRepository.findByName(region.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama wilayah sudah ada !!! ");
        }

        return regionRepository.save(region);
    }

    public Region update(Integer id, Region region) {
         if (regionRepository.findByName(region.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama wilayah sudah ada !!! ");
        }

        getById(id);
        region.setId(id);
        return regionRepository.save(region);
    }

    public Region Delete(Integer id) {
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }

    // native
    public List<Region> searchAllNameNative(String name){
        return regionRepository.searchAllNameNative("%" + name + "%");
    }

    // JPQL
    public List<Region> searchAllNameJPQL(String name){
        return regionRepository.searchAllNameJPQL("%" + name + "%");
    }

    public List<String> getAllName() {
        return regionRepository.getAllName();
      }
}
