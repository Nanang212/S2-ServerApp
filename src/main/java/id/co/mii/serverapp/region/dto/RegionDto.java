package id.co.mii.serverapp.region.dto;

import java.util.List;

public class RegionDto {
    private Integer id;
    private String name;
    private List<CountryDto> countries;

    public RegionDto() {
    }

    public RegionDto(Integer id, String name, List<CountryDto> countries) {
        this.id = id;
        this.name = name;
        this.countries = countries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CountryDto> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryDto> countries) {
        this.countries = countries;
    }

    public static class CountryDto {
        private Integer id;
        private String code;
        private String name;

        public CountryDto() {
        }

        public CountryDto(Integer id, String code, String name) {
            this.id = id;
            this.code = code;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
