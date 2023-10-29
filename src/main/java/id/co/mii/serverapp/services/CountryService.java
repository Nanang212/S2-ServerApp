package id.co.mii.serverapp.services;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;

// @Slf4j
@Service
@AllArgsConstructor
public class CountryService {
    
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private RegionService regionService;
    private ModelMapper modelMapper;

    public List<Country> getAll(){
        return countryRepository.findAll();
    }

    public Country getById(Integer id){
        return countryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found!"));
    }

    public Country create(Country country){

        Optional<Region> regionExist = regionRepository.findByName(country.getName());
        Country countryExist = countryRepository.findByName(country.getName());

        if(regionExist.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country with the same name already exists!");
        }

        if(countryExist != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of the country is the same as the name of the region!");
        }

        if (country.getCode().length() != 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Length of code field must be 2 characters");
        }

        if (countryRepository.findByCode(country.getCode()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Code of the country has already exists!");
        }

        if (country.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name field required!");
        }

        return countryRepository.save(country);
    }

    public Country createDTO(CountryRequest countryRequest) {
        Country country = new Country();
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        Region region = regionService.getById(countryRequest.getRegionId());
        country.setRegion(region);

        // log.info("check region : {}", region);
        return countryRepository.save(country);
    }

    public Country createDTOByModelMapper(CountryRequest countryRequest) {
        Country country = modelMapper.map(countryRequest, Country.class);
        country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepository.save(country);
    }

    
    public Country update(Integer id, Country country){
       
        getById(id);
        country.setId(id);
        
        // Optional<Region> regionExist = regionRepository.findByName(country.getName());
        // Country countryExist = countryRepository.findByName(country.getName());

        // if(countryExist != null){
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country with the same name already exists!");
        // }

        // if(regionExist.isPresent()){
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of the country is the same as the name of the region!");
        // }

        // if (country.getCode().length() != 2) {
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Length of code field must be 2 characters");
        // }

        // if (countryRepository.findByCode(country.getCode()) != null) {
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Code of the country has already exists!");
        // }

        // if (country.getName().isEmpty()) {
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name field required!");
        // }

        return countryRepository.save(country);
    }

    public Country delete(Integer id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }

    // custom manual
    public Map<String, Object> getByIdCustom(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Country country = countryRepository.findById(id).get();

        result.put("countryId", country.getId());
        result.put("countryCode", country.getCode());
        result.put("countryName", country.getName());
        result.put("regionId", country.getRegion().getId());
        result.put("regionName", country.getRegion().getName());

        return result;
    }

  public List<Map<String, Object>> getAllCustom() {
    List<Map<String, Object>> results = new ArrayList<>();
    List<Country> countries = countryRepository.findAll();

    for (Country country : countries) {
      Map<String, Object> result = new HashMap<>();
      result.put("countryId", country.getId());
      result.put("countryCode", country.getCode());
      result.put("countryName", country.getName());
      result.put("regionId", country.getRegion().getId());
      result.put("regionName", country.getRegion().getName());
      results.add(result);
    }
    return results;
  }

  public List<Map<String, Object>> getAllCustomStream() {
    return countryRepository
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
