package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.services.RegionService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController // json
@AllArgsConstructor
@RequestMapping("/region")
public class RegionController {

  private RegionService regionService;

  @GetMapping
  public List<Region> getAll() {
    return regionService.getAll();
  }

  @GetMapping("/{id}")
  public Region getById(@PathVariable Integer id) {
    return regionService.getById(id);
  }

  @PostMapping
  public Region create(@RequestBody Region region) {
    return regionService.create(region);
  }

  @PutMapping("/{id}")
  public Region update(@PathVariable Integer id, @RequestBody Region region) {
    return regionService.update(id, region);
  }

  @DeleteMapping("/{id}")
  public Region delete(@PathVariable Integer id) {
    return regionService.delete(id);
  }

  // Native
  @GetMapping("/native")
  public List<Region> searchAllNameNative(
    @RequestParam(name = "name") String name
  ) {
    return regionService.searchAllNameNative(name);
  }

  // JPQL
  @GetMapping("/jpql")
  public List<Region> searchAllNameJPQL(
    @RequestParam(name = "name") String name
  ) {
    return regionService.searchAllNameJPQL(name);
  }

  @GetMapping("/jpql-all")
  public List<String> getAllName() {
    return regionService.getAllName();
  }
}
