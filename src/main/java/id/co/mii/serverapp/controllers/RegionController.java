package id.co.mii.serverapp.controllers;

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

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.services.RegionServices;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/region")
@AllArgsConstructor
public class RegionController {
    private RegionServices regionServices;

    @GetMapping
    public List<Region> getAll() {
        return regionServices.getAll();
    }

    @GetMapping("/{id}")
    public Region getById(@PathVariable Integer id) {
        return regionServices.getById(id);
    }

    @PostMapping
    public Region create(@RequestBody Region region) {
        return regionServices.create(region);
    }

    @PutMapping("/{id}")
    public Region update(@PathVariable Integer id, @RequestBody Region region) {
        return regionServices.update(id, region);
    }

    @DeleteMapping("/{id}")
    public Region delete(@PathVariable Integer id) {
        return regionServices.delete(id);
    }

    // Native
    @GetMapping("/native")
    public List<Region> searchAllNameNative(
            @RequestParam(name = "name") String name) {
        return regionServices.searchAllNameNative(name);
    }

    // JPQL
    @GetMapping("/jpql")
    public List<Region> searchAllNameJPQL(
            @RequestParam(name = "name") String name) {
        return regionServices.searchAllNameJPQL(name);
    }

    @GetMapping("/jpql-all")
    public List<String> getAllName() {
        return regionServices.getAllName();
    }
}
