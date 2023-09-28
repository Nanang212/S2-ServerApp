package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {
    private CountryService countryService;
    @PostMapping
    public ResponseEntity<Country> create(@RequestBody CountryRequest countryRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(countryService.create(countryRequest));
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllBy(@RequestParam(name = "keyword", required = false) String keyword) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(countryService.getAll(keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(countryService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable Integer id, @RequestBody Country entity) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(countryService.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Country> delete(@PathVariable Integer id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(countryService.delete(id));
    }
}
