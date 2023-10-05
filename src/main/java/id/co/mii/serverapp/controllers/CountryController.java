package id.co.mii.serverapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.dto.CountryRequest;
import id.co.mii.serverapp.services.CountryServices;

import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;

@AllArgsConstructor
// @NoArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {
    private CountryServices countryServices;

    @GetMapping
    public List<Country> getAll() {
        return countryServices.getAll();
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id) {
        return countryServices.getById(id);
    }

    // without dto
    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryServices.create(country);
    }

    // with dto
    @PostMapping("/dto")
    public Country createDTO(@RequestBody CountryRequest countryRequest) {
        return countryServices.createDTO(countryRequest);
    }

    // with dto by model mapper
    @PostMapping("/dto-m")
    public Country createDTOByModelMapper(
            @RequestBody CountryRequest countryRequest) {
        return countryServices.createDTOByModelMapper(countryRequest);
    }

    @PutMapping("/{id}")
    public Country update(
            @PathVariable Integer id,
            @RequestBody Country country) {
        return countryServices.update(id, country);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id) {
        return countryServices.delete(id);
    }

    // custom manual
    @GetMapping("/custom/{id}")
    public Map<String, Object> getByIdCustom(@PathVariable Integer id) {
        return countryServices.getByIdCustom(id);
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllCustom() {
        return countryServices.getAllCustom();
    }

    @GetMapping("/all/stream")
    public List<Map<String, Object>> getAllCustomStream() {
        return countryServices.getAllCustomStream();
    }
}
