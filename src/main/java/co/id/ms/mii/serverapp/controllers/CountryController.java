package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.dto.request.CountryRequest;
import co.id.ms.mii.serverapp.models.Country;
import co.id.ms.mii.serverapp.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/country")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class CountryController {
    private CountryService countryService;

    @GetMapping
    public List<Country> GetAll(){
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public Country GetAll(@PathVariable Integer id){
        return countryService.GetById(id);
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

    // without dto
    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    @PostMapping("/dto")
    public Country create(@RequestBody CountryRequest countrydto){
        return countryService.insert(countrydto);
    }

    @PutMapping("/{id}")
    public Country update(
            @PathVariable Integer id,
            @RequestBody Country country
    ) {
        return countryService.update(id, country);
    }

    @PutMapping("/dto/{id}")
    public Country update(@PathVariable Integer id,@RequestBody CountryRequest countrydto){
        return countryService.updateDto(countrydto,id);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id){
        return countryService.delete(id);
    }
}
