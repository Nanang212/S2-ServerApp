package id.co.mii.serverapp.util;

import id.co.mii.serverapp.country.Country;
import id.co.mii.serverapp.country.dto.CountryDto;
import id.co.mii.serverapp.region.Region;
import id.co.mii.serverapp.region.dto.RegionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    public RegionDto toDto(Region region) {
        RegionDto regionDto = new RegionDto();
        regionDto.setId(region.getId());
        regionDto.setName(region.getName());
        List<RegionDto.CountryDto> countryDtos = new ArrayList<>();
        regionDto.setCountries(countryDtos);

        if (region.getCountries() != null) {
            region.getCountries().forEach(country -> {
                countryDtos.add(new RegionDto.CountryDto(country.getId(), country.getCode(), country.getName()));
            });
        }

        return regionDto;
    }

    public CountryDto toDto(Country country) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setCode(country.getCode());
        countryDto.setName(country.getName());
        countryDto.setRegion(new CountryDto.RegionDto(country.getRegion().getId(), country.getRegion().getName()));

        return countryDto;
    }
}
