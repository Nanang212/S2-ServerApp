package id.co.mii.serverapp.country.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class CountryUpdateDto {
    @NotNull
    @Positive
    private Integer regionId;
    @NotNull
    @Size(min = 2, max = 2)
    private String code;
    @NotNull
    @Size(min = 1, max = 64)
    private String name;

    public CountryUpdateDto() {
    }

    public CountryUpdateDto(Integer regionId, String code, String name) {
        this.regionId = regionId;
        this.code = code;
        this.name = name;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
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
