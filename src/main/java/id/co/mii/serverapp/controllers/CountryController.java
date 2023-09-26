package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.controllers.base.BaseController;
import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepo;
import id.co.mii.serverapp.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController extends BaseController<Country> {
    @Autowired
    private CountryService countryService;

    @GetMapping("/search")
    public ResponseEntity<List<Country>> getAllBy(@RequestParam(name = "keyword", required = false) String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body( countryService.getAll(keyword));
    }
}
