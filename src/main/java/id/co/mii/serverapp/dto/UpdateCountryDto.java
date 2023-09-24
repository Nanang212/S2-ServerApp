package id.co.mii.serverapp.dto;

public class UpdateCountryDto {
    private String name;
    private String code;
    private Integer regionId;

    public UpdateCountryDto() {
    }

    public UpdateCountryDto(String name, String code, Integer regionId) {
        this.name = name;
        this.code = code;
        this.regionId = regionId;
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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

}
