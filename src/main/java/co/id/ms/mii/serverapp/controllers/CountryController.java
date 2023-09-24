package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.models.Country;
import co.id.ms.mii.serverapp.services.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/country")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> GetAll(){
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public Country GetAll(@PathVariable Integer id){
        return countryService.GetAllById(id);
    }

    @PostMapping
    public Country create(@RequestBody Country country){
        return countryService.insert(country);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable Integer id,@RequestBody Country country){
        return countryService.update(country,id);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id){
        return countryService.delete(id);
    }
}
