package id.co.mii.serverapp.services;
import org.modelmapper.ModelMapper;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.CountryRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryService {

//   @Autowired
  private CountryRepository  countryRepo;
  private RegionRepository regionRepository;
  private RegionService regionService;
  private ModelMapper modelMapper;
    
    // public CountryService(CountryRepository countryRepo) {
    //     this.countryRepo = countryRepo;
    // }

 
    // public CountryService(CountryRepository countryRepo, RegionRepository regionRepository) {
    //     this.countryRepo = countryRepo;
    //     this.regionRepository = regionRepository;
    // }

     public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }
    //with dto
    public Country create(Country country){
        if(countryRepo.existsByName(country.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"country name is exit");
        
        }
         return countryRepo.save(country);
    }
    //with dto
    public Country createDTO (CountryRequest countryRequest){
        Country country = new Country();
        country.setName(countryRequest.getCode());
        country.setName(countryRequest.getName());

        Region region = regionService.getById(countryRequest.getRegionId());
        // kenapa return type nya si region?
        country.setRegion(region);

        return countryRepo.save(country);
    }
    // apa yang membedakan create dan createDTO , pada saat kapan digunakan?
    

    //with dto model mapper
    //modelmapper digunakan untuk pemetaan sebuah objek dan perubahan dengan secara otomatis
    //maps digunakan untuk lebih ke strukur  dan berbeda
    // pada saat kapan digunakan?

    public Country createDTOByModelMapper(CountryRequest countryRequest){
        Country country = modelMapper.map(countryRequest,Country.class);

        country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepo.save(country);
    }
    public Optional<Country> getCountryById(Integer id) {
        return countryRepo.findById(id);
    }

    public Country createCountry(Country country) {
        if (regionRepository.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Failed not found country ");
            //country tidak boleh sama dengan region      
        }
      if (countryRepo.existsByName(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Failed not found ");
            //country tidak boleh sama dengan region      
        }
        return countryRepo.save(country);

    }


    public Country updateCountry(Integer id, Country updatedCountry) {
        Optional<Country> existingCountry = countryRepo.findById(id);
        if (existingCountry.isPresent()) {
            Country country = existingCountry.get();
            country.setCode(updatedCountry.getCode());
            country.setName(updatedCountry.getName());
            return countryRepo.save(country);
        }
        return null;
        //null  jika nilai atau data tidak boleh memiliki maka akan dinyatakan true 
     }

    public void deleteCountry(Integer id) {
        countryRepo.deleteById(id);
    }
    //custom manual
    public Map<String, Object> getByIdCustom(Integer id) {
    Map<String, Object> result = new HashMap<>();
    Country country = countryRepo.findById(id).get();

    result.put("countryId", country.getId());
    result.put("countryCode", country.getCode());
    result.put("countryName", country.getName());
    result.put("regionId", country.getRegion().getId());
    result.put("regionName", country.getRegion().getName());

    //kenapa result.put (K,V)?

    return result;
  }
   public List<Map<String,Object>> getAllCustom() {
    List<Map<String, Object>> results = new ArrayList<>();
    // List<Map<String,Objek>> apakah untuk modelmapper nya?
    List<Country> countries = countryRepo.findAll();

    for (Country country : countries) {
      Map<String, Object> result = new HashMap<>();
      result.put("countryId", country.getId());
      result.put("countryCode", country.getCode());
      result.put("countryName", country.getName());
      result.put("regionId", country.getRegion().getId());
      result.put("regionName", country.getRegion().getName());

      return results;
    }
    return results;

    }
    public List<Map<String, Object>> getAllCustomStream() {
        return countryRepo
          .findAll()
          .stream()
          .map(country -> {
            Map<String, Object> result = new HashMap<>();
            result.put("countryId", country.getId());
            result.put("countryCode", country.getCode());
            result.put("countryName", country.getName());
            result.put("regionId", country.getRegion().getId());
            result.put("regionName", country.getRegion().getName());
            return result;
          })
          .collect(Collectors.toList());
      }
    }


