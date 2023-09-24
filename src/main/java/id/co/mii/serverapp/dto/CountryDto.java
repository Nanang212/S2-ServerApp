package id.co.mii.serverapp.dto;

import id.co.mii.serverapp.dto.base.BaseDto;

public class CountryDto extends BaseDto {
    private String code;
    private String regionId;

    public CountryDto() {
    }

    public CountryDto(String name, String code, String regionId) {
        super(name);
        this.code = code;
        this.regionId = regionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
