package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.controllers.base.BaseController;
import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepo;
import id.co.mii.serverapp.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController extends BaseController<Country> {
}
