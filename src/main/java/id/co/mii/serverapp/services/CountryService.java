package id.co.mii.serverapp.services;

import id.co.mii.serverapp.dto.CountryDto;
import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.repositories.CountryRepo;
import id.co.mii.serverapp.repositories.RegionRepo;
import id.co.mii.serverapp.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CountryService extends BaseService<Country> {
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private RegionRepo regionRepo;

    @Override
    public Country create(Country country) {
        if (regionRepo.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already belong to region");
        }
        if (countryRepo.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country name is already used");
        }
        if (countryRepo.existsByCode(country.getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country code is already used");
        }
        return countryRepo.save(country);
    }
}
