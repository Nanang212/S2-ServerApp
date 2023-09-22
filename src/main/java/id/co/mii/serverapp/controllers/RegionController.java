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
import org.springframework.web.bind.annotation.RestController;

@RestController // json
@RequestMapping("/region")
public class RegionController {

  private RegionService regionService;

  public RegionController(RegionService regionService) {
    this.regionService = regionService;
  }

  @GetMapping
  public List<Region> getAll() {
    return regionService.getAll();
  }

  // http://localhost:9000/region/1    = path variable
  // http://localhost:9000/region?id=1 = path param

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
}
