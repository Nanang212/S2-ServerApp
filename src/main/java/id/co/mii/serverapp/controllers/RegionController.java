package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.services.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@AllArgsConstructor
public class RegionController {
    private RegionService regionService;

    @PostMapping
    public ResponseEntity<Region> create(@RequestBody Region region) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(regionService.create(region));
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(regionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(regionService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> update(@PathVariable Integer id, @RequestBody Region region) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(regionService.update(id, region));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Region> delete(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(regionService.delete(id));
    }
}
