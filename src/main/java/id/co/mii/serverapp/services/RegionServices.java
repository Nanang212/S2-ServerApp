package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepositories;

import lombok.AllArgsConstructor;



// dalam service dan controller, tidak boleh ada 2 constructor
@AllArgsConstructor
@Service
public class RegionServices {
    private RegionRepositories regionRepositories;

    // public RegionServices() {
    // }

    // public RegionServices(RegionRepositories regionRepositories) {
    //     this.regionRepositories = regionRepositories;
    // }

    public List<Region> getAll() {
        return regionRepositories.findAll();
    }

    public Region getById(Integer id) {
        return regionRepositories
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found!!!"));
    }

    public Region create(Region region) {
        if (regionRepositories.findByName(region.getName()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Name is already exists!!!");
        }
        return regionRepositories.save(region);
    }

    public Region update(Integer id, Region region) {
        getById(id);
        region.setId(id);
        return regionRepositories.save(region);
    }

    public Region delete(Integer id) {
        Region region = getById(id);
        regionRepositories.delete(region);
        return region;
    }

    // Native
    public List<Region> searchAllNameNative(String name) {
        return regionRepositories.searchAllNameNative("%" + name + "%");
    }

    // JPQL
    public List<Region> searchAllNameJPQL(String name) {
        return regionRepositories.searchAllNameJPQL("%" + name + "%");
    }

    public List<String> getAllName() {
        return regionRepositories.getAllName();
    }
}
