package id.co.mii.serverapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
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
import lombok.AllArgsConstructor;


//countroler inputannya json
//restconter inputannya html dan url 

@RestController
@AllArgsConstructor //ini tidak boleh ada duplicate construtor dari countrycontoller (countryservice) 
//diperlukan ketika constructornya dengan banyak prmter
@RequestMapping("country")
public class CountryController {
    @Autowired
    private final CountryService countryService;

    // public CountryController(CountryService countryService) {
    //     this.countryService = countryService;
    // }

    @GetMapping
    public List<Country> getAllCountries() {
        
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Optional<Country> getCountryById(@PathVariable Integer id) { //pathvariabel untuk merequest yang dimana request nya itu berisi id , sedangkan requestbody sama cuman id=name
        return countryService.getCountryById(id);
    }

    @PostMapping
    public Country createCountry(@RequestBody Country country) { //menerima struktur data dari country 
        return countryService.createCountry(country);

    }
    

    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable Integer id, @RequestBody Country updatedCountry) {
        return countryService.updateCountry(id, updatedCountry);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Integer id) {
        countryService.deleteCountry(id);
    }
}
