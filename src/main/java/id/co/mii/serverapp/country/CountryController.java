package id.co.mii.serverapp.country;

import id.co.mii.serverapp.country.dto.CountryCreationDto;
import id.co.mii.serverapp.country.dto.CountryDto;
import id.co.mii.serverapp.country.dto.CountryUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(
        value = "/country",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDto create(@RequestBody CountryCreationDto countryCreationDto) {
        return countryService.create(countryCreationDto);
    }

    @GetMapping(
        value = "/countries",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<CountryDto> getALl() {
        return countryService.getAll();
    }

    @GetMapping(
        value = "/country/{countryId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CountryDto getById(@PathVariable Integer countryId) {
        return countryService.getById(countryId);
    }

    @PutMapping(
        value = "/country/{countryId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CountryDto update(
        @PathVariable Integer countryId,
        @RequestBody CountryUpdateDto countryUpdateDto
    ) {
        return countryService.update(countryId, countryUpdateDto);
    }

    @DeleteMapping(
        value = "/country/{countryId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CountryDto delete(@PathVariable Integer countryId) {
        return countryService.delete(countryId);
    }
}
