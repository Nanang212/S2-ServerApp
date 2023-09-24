package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.dto.CreateCountryDto;
// import id.co.mii.serverapp.dto.CreateRegionDto;
import id.co.mii.serverapp.dto.UpdateCountryDto;

import id.co.mii.serverapp.models.Country;

import id.co.mii.serverapp.services.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryControll {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAll() {
        return countryService.getAllCountry();
    }

    @GetMapping("/{countryId}")
    public Country getBYId(@PathVariable Integer country_id) {
        return countryService.getById(country_id);
    }

    @DeleteMapping("/{countryId}")
    public boolean deleteCountry(@PathVariable Integer country_id) {
        countryService.deleteCountry(country_id);
        return true;
    }

    @PutMapping("/{countryId}")
    public Country updateCountry(@PathVariable Integer id, @RequestBody UpdateCountryDto dto) {
        Country country = countryService.updateCountry(id, dto);
        return country;
    }

    @PostMapping
    public Country createCountry(@RequestBody CreateCountryDto dto) {
        Country country = countryService.createCountry(dto);
        return country;
    }

}
