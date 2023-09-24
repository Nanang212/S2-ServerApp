package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.services.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAll(){
        return countryService.getAll();
    }
    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id){
        return countryService.getById(id);
    }
    @PostMapping
    public Country create(@RequestBody Country country){
        return countryService.create(country);
    }
    @PutMapping("/{id}")
    public Country update(@PathVariable Integer id, @RequestBody Country country){
        return countryService.update(id, country);
    }
    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id){
        return countryService.delete(id);
    }
}   
