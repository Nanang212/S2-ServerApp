package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepository;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        .orElseThrow(() -> new 
        ResponseStatusException(
        HttpStatus.NOT_FOUND, 
        "Region not found!!!"));
  }

  public Region create(Region region) {
    if (regionRepository.findByName(region.getName()).isPresent()) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT,
          "Name is already exists!!!");
    }

    return regionRepository.save(region);
  }

  public Region update(Integer id, Region region) {
    getById(id);
    region.setId(id);
    return regionRepository.save(region);
  }

  public Region delete(Integer id) {
    Region region = getById(id);
    regionRepository.delete(region);
    return region;
  }

  // Native
  public List<Region> searchAllNameNative(String name) {
    return regionRepository.searchAllNameNative("%" + name + "%");
  }

  // JPQL
  public List<Region> searchAllNameJPQL(String name) {
    return regionRepository.searchAllNameJPQL("%" + name + "%");
  }

  public List<String> getAllName() {
    return regionRepository.getAllName();
  }
}
