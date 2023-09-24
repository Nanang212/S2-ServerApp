package co.id.mii.serverapp.controllers;

import java.util.List;

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
import co.id.mii.serverapp.services.CountryServices;

@Controller
@RestController
@RequestMapping("/country")

public class CountryController {
    private CountryServices countryservices;

    public CountryController(CountryServices countryservices){
        this.countryservices = countryservices;
    }

    @GetMapping
    public List<Country> getAll(){
        return countryservices.getAll();
    }

    @GetMapping("/{id}")
    public Country getAll(@PathVariable Integer id){
        return countryservices.getAllById(id);
    }
    @PostMapping
    public Country create(@RequestBody Country country){
        return countryservices.insert(country);
    }
    @PutMapping ("/{id}")
    public Country update(@PathVariable Integer id, @RequestBody Country country){
        return countryservices.update(country,id);
    }
    @DeleteMapping ("/{id}")
    public Country delete(@PathVariable Integer id){
        return countryservices.delete(id);
    }
}