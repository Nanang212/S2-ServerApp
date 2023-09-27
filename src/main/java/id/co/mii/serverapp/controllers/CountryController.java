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
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.services.CountryService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {
    
    private CountryService countryService;

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
    public Country update(@RequestBody  Country country,@PathVariable Integer id){
        return countryService.update(country, id);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id){
        return countryService.delete(id);
    }

    @PostMapping("/manual-dto")
    public Country createWithManualDTO(@RequestBody CountryRequest request){
        return countryService.createWithManualDTO(request);
    }

    @PostMapping("/model-mapper-dto")
    public Country createDTOWithModelMapper(@RequestBody CountryRequest request){
        return countryService.createDTOWithModelMapper(request);
    }

    @GetMapping("/id-using-map/{id}")
    public Map<String, Object> getByIdCustomUsingMap(@PathVariable Integer id){
        return countryService.getByIdCustomUsingMap(id);
    }

    @GetMapping("/getAll-using-ListOfMap")
    public List<Map<String, Object>> getAllCustomUsingListOfMap(){
        return countryService.getAllCustomUsingListOfMap();
    }

    @GetMapping("/getAll-stream")
    public List<Map<String, Object>> getAllCustomStream (){
        return countryService.getAllCustomStream();
    }
}
