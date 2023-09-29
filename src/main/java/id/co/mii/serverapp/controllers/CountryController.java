package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.services.CountryService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;

    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id) {
        return countryService.getById(id);
    }

    // without dto
    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    // with dto
    @PostMapping("/dto")
    public Country createDTO(@RequestBody CountryRequest countryRequest) {
        return countryService.createDTO(countryRequest);
    }

    // with dto by model mapper
    @PostMapping("/dto-m")
    public Country createDTOByModelMapper(@RequestBody CountryRequest countryRequest) {
        return countryService.createDTOByModelMapper(countryRequest);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable Integer id, @RequestBody Country country) {
        return countryService.update(id, country);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id) {
        return countryService.delete(id);
    }

    // custom manual
    @GetMapping("/custom/{id}")
    public Map<String, Object> getByIdCustom(@PathVariable Integer id) {
        return countryService.getByIdCustom(id);
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllCustom() {
        return countryService.getAllCustom();
    }

    @GetMapping("/all/stream")
    public List<Map<String, Object>> getAllCustomStream() {
        return countryService.getAllCustomStream();
    }
}