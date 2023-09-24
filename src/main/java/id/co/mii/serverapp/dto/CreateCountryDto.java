package id.co.mii.serverapp.dto;

public class CreateCountryDto {
    private String name;
    private String code;
    private String region;

    public CreateCountryDto() {
    }

    public CreateCountryDto(String name, String code, String region) {
        this.name = name;
        this.code = code;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    // public CreateCountryDto() {
    // }

    // public CreateCountryDto(String name) {
    // this.name = name;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

}
