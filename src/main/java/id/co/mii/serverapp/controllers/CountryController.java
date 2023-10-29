package id.co.mii.serverapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
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

@RestController
@AllArgsConstructor
@RequestMapping("/country")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class CountryController {
    private CountryService countryService;


    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Country findByIdCountry(@PathVariable Integer id) {
        return countryService.getByIdCountry(id);
    }

    @PostMapping
    public Country inserCountry(@RequestBody Country country){
    return countryService.insert(country);
    }

    //create country with dto
    @PostMapping("/dto")
    public Country insertCountryDto(@RequestBody CountryRequest countryRequest){
        return countryService.createDTO(countryRequest);
    }

    //withDtoByModelMapper
    @PostMapping("/dto-m")
    public Country createDtoByModelMapper(@RequestBody CountryRequest countryRequest){
        return countryService.createDtoByModelMapper(countryRequest);
    }

    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable Integer id, @RequestBody Country country) {
        return countryService.update(id, country);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id) {
        return countryService.delete(id);
    }

    //get custom manual
    @GetMapping("/custom/{id}")
    public Map<String, Object> getByIdCustom(@PathVariable Integer id){
        return countryService.getByIdCustom(id);
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllCustom(){
        return countryService.getAllCustom();
    }

    @GetMapping("/all/stream")
    public List<Map<String, Object>> getAllCustomStream(){
        return countryService.getAllCustomStream();
    }

}
