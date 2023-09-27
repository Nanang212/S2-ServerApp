package id.co.mii.serverapp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CountryService {
    
    private CountryRepository countryRepository;

    private RegionsRepository regionsRepository;

    private RegionService regionService;

    private ModelMapper modelMapper;

    public List<Country> getAll(){
        return countryRepository.findAll();
    }

    public Country getById(Integer id){
        return countryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak ada id :" + id));
    }

    public Country create(Country country){
        if(countryRepository.existsByName(country.getName())){
            throw  new ResponseStatusException(HttpStatus.CONFLICT,"name country already exist");
        } 

        if(regionsRepository.findByName(country.getName()).isPresent()){
             throw  new ResponseStatusException(HttpStatus.CONFLICT,"name region already exist");
        }

        return countryRepository.save(country);
    }

  
    public Country update(Country country, Integer id){
        getById(id);
        country.setId(id);
        return create(country);
    }

    public Country delete(Integer id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }

    public Country createWithManualDTO(CountryRequest request){
        Country country = new Country();
        country.setCode(request.getCode());
        country.setName(request.getName());
        
        Region region = regionService.getById(request.getRegionId());
           
        country.setRegion(region);

        return countryRepository.save(country);
    }

  public Country createDTOWithModelMapper(CountryRequest request){
    Country country = modelMapper.map(request, Country.class);

    country.setRegion(regionService.getById(request.getRegionId()));

    return countryRepository.save(country);
  }

  public Map<String, Object> getByIdCustomUsingMap(Integer id){
    Map<String, Object> result = new HashMap<>();

    Country country = countryRepository.findById(id).get();

    result.put("countryId", country.getId());
    result.put("countryCode", country.getCode());
    result.put("countryName", country.getName());
    result.put("regionId", country.getRegion().getId());
    result.put("regionName", country.getRegion().getName());

    return result;
  }
}



