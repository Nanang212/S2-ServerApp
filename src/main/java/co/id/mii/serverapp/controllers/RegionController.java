package co.id.mii.serverapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.mii.serverapp.models.Region;
import co.id.mii.serverapp.services.RegionServices;

@Controller
@RestController //json ouput
@RequestMapping("/region")

public class RegionController {
    private RegionServices regionServices;

    
    public RegionController(RegionServices regionServices) {
        this.regionServices = regionServices;
    }
    @GetMapping
    public List<Region> findAll() {
        return regionServices.getAll();
    }
    
    @GetMapping("/{Id}")
    public Region findbyId(@PathVariable Integer Id){
        return regionServices.getById(Id);
    }
    @PostMapping
    public Region create(@RequestBody Region region){
        return regionServices.create(region);
    }
    @PutMapping("/{Id}")
    public Region update(@PathVariable Integer Id, @RequestBody Region region){
        return regionServices.update(Id, region);
    }
    @DeleteMapping("/{Id}")
    public Region delete (@PathVariable Integer Id){
        return regionServices.delete(Id);
    }

}
