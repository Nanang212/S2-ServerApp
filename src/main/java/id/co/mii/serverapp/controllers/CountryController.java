package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.services.CountryService;
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
import java.util.Map;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(
        value = "/country",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Country create(@RequestBody CountryRequest request) {
        return countryService.create(request);
    }

    @GetMapping(
        value = "/countries",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Country> getALl() {
        return countryService.getAll();
    }

    @GetMapping(
        value = "/country/{countryId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Country getById(@PathVariable Integer countryId) {
        return countryService.getById(countryId);
    }

    @PutMapping(
        value = "/country/{countryId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Country update(
        @PathVariable Integer countryId,
        @RequestBody CountryRequest request
    ) {
        return countryService.update(countryId, request);
    }

    @DeleteMapping(
        value = "/country/{countryId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Country delete(@PathVariable Integer countryId) {
        return countryService.delete(countryId);
    }

    // custom manual
    @GetMapping(
        value = "/country/custom/{countryId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Map<String, Object> getByIdCustom(@PathVariable Integer countryId) {
        return countryService.getByIdCustom(countryId);
    }

    @GetMapping(
        value = "/countries/all",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Map<String, Object>> getAllCustom() {
        return countryService.getAllCustom();
    }

    @GetMapping(
        value = "/countries/all/stream",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Map<String, Object>> getAllCustomStream() {
        return countryService.getAllCustomStream();
    }
}
