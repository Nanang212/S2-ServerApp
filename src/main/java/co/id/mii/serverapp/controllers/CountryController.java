package co.id.mii.serverapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.id.mii.serverapp.models.Country;
import co.id.mii.serverapp.models.dto.request.CountryRequest;
import co.id.mii.serverapp.services.CountryServices;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor // mempersingkat consktuktor
@RequestMapping("/country")

public class CountryController {
    private CountryServices countryservices;

    @GetMapping
    public List<Country> getAll() {
        return countryservices.getAll();
    }

    @GetMapping("/{id}")
    public Country getAll(@PathVariable Integer id) {
        return countryservices.getAllById(id);
    }

    // tanpa dto
    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryservices.insert(country);
    }

    // with dto
    @PostMapping("/dto")
    public Country createDTO(@RequestBody CountryRequest countryRequest) {
        return countryservices.createDTO(countryRequest);
    }

    // with dto by model mapper
    @PostMapping("/dto-m")
    public Country createDTOByModelMapper(
            @RequestBody CountryRequest countryRequest) {
        return countryservices.createDTOByModelMapper(countryRequest);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable Integer id, @RequestBody Country country) {
        return countryservices.update(country, id);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id) {
        return countryservices.delete(id);
    }

    // custom manual
    @GetMapping("/custom/{id}")
    public Map<String, Object> getByIdCustom(@PathVariable Integer id) {
        return countryservices.getByIdCustom(id);
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllCustom() {
        return countryservices.getAllCustom();
    }

    @GetMapping("/all/stream")
    public List<Map<String, Object>> getAllCustomStream() {
        return countryservices.getAllCustomStream();
    }
}