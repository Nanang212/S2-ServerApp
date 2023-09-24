package id.co.mii.serverapp.controllers;

import java.util.List;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.dto.CreateRegionDto;
import id.co.mii.serverapp.dto.UpdateRegionDTO;
import id.co.mii.serverapp.models.Region;
// outputnya view (html)
import id.co.mii.serverapp.services.RegionService;

// @Controller
// outputnya json
@RestController
// controller ini seperti view di netbeans
@RequestMapping("/region")
public class RegionController {
    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> getAll() {
        return regionService.getAllRegions();
    }

    @GetMapping("/{regionId}")
    public Region getBYId(@PathVariable Integer regionId) {
        return regionService.getById(regionId);
    }

    @DeleteMapping("/{regionId}")
    public boolean deleteRegion(@PathVariable Integer regionId) {
        regionService.deleteRegion(regionId);
        return true;
    }

    @PutMapping("/{regionId}")
    public Region updateRegion(@PathVariable Integer regionId, @RequestBody UpdateRegionDTO dto) {
        Region region = regionService.updateRegion(regionId, dto);
        return region;
    }

    @PostMapping("/region_name")
    public Region createRegion(@RequestBody CreateRegionDto dto) {
        Region region = regionService.createRegion(dto);
        return region;
    }

}
