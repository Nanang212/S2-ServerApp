package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.services.RegionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // json
@AllArgsConstructor
@RequestMapping("/region")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class RegionController {

  private RegionService regionService;

  @PreAuthorize("hasAnyAuthority('READ-ADMIN', 'READ-USER')")
  @GetMapping
  public List<Region> getAll() {
    return regionService.getAll();
  }

  // http://localhost:9000/region/1    = path variable
  // http://localhost:9000/region?id=1 = path param

  @PreAuthorize("hasAnyAuthority('READ-ADMIN', 'READ-USER')")
  @GetMapping("/{id}")
  public Region findById(@PathVariable Integer id) {
      return regionService.findById(id);
  }

   @PreAuthorize("hasAuthority('CREATE-ADMIN')")
   @PostMapping()
   public Region create(@RequestBody Region region) {
       return regionService.insertRegion(region);
   }

  @PreAuthorize("hasAuthority('UPDATE-ADMIN')")
  @PutMapping("/{id}")
  public Region update(@PathVariable Integer id, @RequestBody Region region) {
    return regionService.update(id, region);
  }

  @DeleteMapping("/{id}")
  public Region delete(@PathVariable Integer id) {
    return regionService.delete(id);
  }
}
