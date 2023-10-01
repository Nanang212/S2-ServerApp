package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.request.RegionRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;
    private final Validator validator;

    public Region create(RegionRequest request) {
        Set<ConstraintViolation<RegionRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (regionRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name already exists. Use another name");
        }

        if (countryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The region name and the country name must not be the same");
        }

        Region region = new Region();
        region.setName(request.getName());

        return regionRepository.save(region);
    }

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region getById(Integer regionId) {
        return regionRepository
            .findById(regionId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found"));
    }

    public Region update(Integer regionId, RegionRequest request) {
        Region region = regionRepository
            .findById(regionId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region is not found"));
        region.setName(request.getName());

        if (countryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The region name and the country name must not be the same");
        }

        return regionRepository.save(region);
    }

    public Region delete(Integer regionId) {
        Region region = regionRepository
            .findById(regionId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region is not found"));

        regionRepository.delete(region);

        return region;
    }

    // Native
    public List<Region> searchAllNameNative(String name) {
        return regionRepository.searchAllNameNative("%" + name + "%");
    }

    // JPQL
    public List<Region> searchAllNameJPQL(String name) {
        return regionRepository.searchAllNameJPQL("%" + name + "%");
    }

    public List<String> getAllName() {
        return regionRepository.getAllName();
    }
}