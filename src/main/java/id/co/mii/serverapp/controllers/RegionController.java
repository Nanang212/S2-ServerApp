package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.requests.RegionRequest;
import id.co.mii.serverapp.services.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping(
        value = "/region",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Region create(@RequestBody RegionRequest regionRequest) {
        return regionService.create(regionRequest);
    }

    @GetMapping(
        value = {"/region", "/regions"},
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Region> getAll() {
        System.out.println(regionService.getAll());
        return regionService.getAll();
    }

    @GetMapping(
        value = "/region/{regionId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Region getById(@PathVariable Integer regionId) {
        return regionService.getById(regionId);
    }

    @PutMapping(
        value = "/region/{regionId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Region update(
        @PathVariable Integer regionId,
        @RequestBody RegionRequest regionRequest
    ) {
        return regionService.update(regionId, regionRequest);
    }

    @DeleteMapping(
        value = "/region/{regionId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Region delete(@PathVariable Integer regionId) {
        return regionService.delete(regionId);
    }


    // Native
    @GetMapping(
        value = "/regions/native",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Region> searchAllNameNative(
        @RequestParam(name = "name") String name
    ) {
        return regionService.searchAllNameNative(name);
    }

    // JPQL
    @GetMapping(
        value = "/regions/jpql",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Region> searchAllNameJPQL(
        @RequestParam(name = "name") String name
    ) {
        return regionService.searchAllNameJPQL(name);
    }

    @GetMapping(
        value = "/regions/jpql-all",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<String> getAllName() {
        return regionService.getAllName();
    }
}
