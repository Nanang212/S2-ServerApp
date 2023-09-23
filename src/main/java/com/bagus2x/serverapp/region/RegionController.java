package com.bagus2x.serverapp.region;

import com.bagus2x.serverapp.region.dto.RegionCreationDto;
import com.bagus2x.serverapp.region.dto.RegionDto;
import com.bagus2x.serverapp.region.dto.RegionUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public RegionDto create(@RequestBody RegionCreationDto regionCreationDto) {
        return regionService.create(regionCreationDto);
    }

    @GetMapping(
        value = "/regions",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<RegionDto> getAll() {
        System.out.println(regionService.getAll());
        return regionService.getAll();
    }

    @GetMapping(
        value = "/region/{regionId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegionDto getById(@PathVariable Integer regionId) {
        return regionService.getById(regionId);
    }

    @PutMapping(
        value = "/region/{regionId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegionDto update(
        @PathVariable Integer regionId,
        @RequestBody RegionUpdateDto dto
    ) {
        return regionService.update(regionId, dto);
    }

    @DeleteMapping(
        value = "/region/{regionId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegionDto delete(@PathVariable Integer regionId) {
        return regionService.delete(regionId);
    }
}
