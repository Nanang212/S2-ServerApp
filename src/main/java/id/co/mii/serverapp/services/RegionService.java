package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;

@Service
public class RegionService {
    private RegionRepository regionRepository;
    private CountryRepository countryRepository;

    public RegionService(RegionRepository regionRepository, CountryRepository countryRepository) {
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
    }

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region getById(Integer id) {
        return regionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found !!!"));
    }

    public Region create(Region region) {

        if (regionRepository.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region sudah ada");
        }

        return regionRepository.save(region);
    }

    public Region update(Integer id, Region region) {

        getById(id);
        region.setId(id);

        if (regionRepository.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama Country sudah ada");
        }

        if (countryRepository.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country tidak boleh sama dengan Region");
        }

        return regionRepository.save(region);
    }

    public Region delete(Integer id) {
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }

}
