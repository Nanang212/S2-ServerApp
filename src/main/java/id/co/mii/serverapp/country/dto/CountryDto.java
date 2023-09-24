package id.co.mii.serverapp.country.dto;

public class CountryDto {
    private Integer id;
    private RegionDto region;
    private String code;
    private String name;

    public CountryDto() {
    }

    public CountryDto(Integer id, RegionDto region, String code, String name) {
        this.id = id;
        this.region = region;
        this.code = code;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RegionDto getRegion() {
        return region;
    }

    public void setRegion(RegionDto region) {
        this.region = region;
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

    public static class RegionDto {
        private Integer id;
        private String name;

        public RegionDto() {
        }

        public RegionDto(Integer id, String name) {
            this.id = id;
            this.name = name;
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
    }
}
