package co.id.ms.mii.serverapp.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.ms.mii.serverapp.models.Region;
import co.id.ms.mii.serverapp.services.RegionServices;

@Controller // view => html
@RestController // json
@AllArgsConstructor
@RequestMapping("/regions")
@PreAuthorize("hasRole('ADMIN')")
public class RegionController {
    private RegionServices regionServices;

    @GetMapping
    public List<Region> findAll() {
        return regionServices.getAll();
    }

    @GetMapping("/{Id}")
    public Region findById(@PathVariable Integer Id){
        return regionServices.getById(Id);
    }

    @PostMapping
    public Region create(@RequestBody Region region){
        return regionServices.create(region);
    }

    @PutMapping("/{id}")
    public Region Update(@PathVariable Integer id,@RequestBody Region region){
        return regionServices.Update(id, region);
    }

    @DeleteMapping("/{id}")
    public Region delete(@PathVariable Integer id){
        return regionServices.delete(id);
    }
}
