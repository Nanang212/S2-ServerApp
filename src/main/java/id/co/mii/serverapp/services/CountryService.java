package id.co.mii.serverapp.services;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.dto.CreateCountryDto;
// import id.co.mii.serverapp.dto.CreateRegionDto;
import id.co.mii.serverapp.dto.UpdateCountryDto;
// import id.co.mii.serverapp.dto.UpdateRegionDTO;
import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.CountryRepository;
// import id.co.mii.serverapp.repositories.RegionRepository;

@Service

public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionService regionService;

    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    public Country getById(int Id) {
        return countryRepository.findById(Id).get();
    }

    public void deleteCountry(int id) {
        countryRepository.deleteById(id);
    }

    public Country updateCountry(int id, UpdateCountryDto dto) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country != null) {
            country.setName(dto.getName());
            return countryRepository.save(country);
        } else {
            throw new IllegalArgumentException("Country dengan ID " + id + " tidak ditemukan");
        }
    }

    // metode ini mengirimkan permintaan ke countryRepository untuk melakukan
    // penghitungan berapa banyak entri yang memiliki nama Region dan Country yang
    // diberikan.
    // hasil nya dari perhitungan ini kemudian dibandingkan dengan 0 (yang berarti
    // nama tersebut adalah unique)
    public boolean isRegionAndCountryHasUniqueName(String regionName, String countryName) {
        return countryRepository.countRegionAndCountryName(regionName, countryName) == 0;
    }

    public Country createCountry(CreateCountryDto dto) {
        String countryCode = dto.getCode();
        String countryName = dto.getName();
        // Ubah tipe data String menjadi Integer
        Integer regionId = Integer.parseInt(dto.getRegion());

        // Dapatkan semua regions dari repository
        List<Region> regions = regionService.getAllRegions();

        // Saring regions untuk mendapatkan region dengan id yang sesuai
        Region region = regions.stream()
                .filter(r -> r.getId().equals(regionId))
                .findFirst()
                .orElse(null);

        if (region == null) {
            throw new IllegalArgumentException("Region dengan ID tersebut tidak ditemukan");
        }

        for (Region region1 : regions) {
            int count = countryRepository.countRegionAndCountryName(region1.getName(), countryName);
            if (count > 0) {
                throw new IllegalArgumentException(
                        "Nama country tidak boleh sama dengan nama Region yang sudah ada sebelumnya");
            }
        }

        Country country = new Country();
        country.setCode(countryCode);
        country.setName(countryName);
        country.setRegion(region);

        Country saveCountry = countryRepository.save(country);
        return saveCountry;
    }

}
