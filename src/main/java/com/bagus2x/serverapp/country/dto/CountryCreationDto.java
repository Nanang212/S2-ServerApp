package com.bagus2x.serverapp.country.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CountryCreationDto {
    @Positive
    private Integer regionId;
    @NotBlank
    private String code;
    @NotBlank
    private String name;

    public CountryCreationDto() {
    }

    public CountryCreationDto(Integer regionId, String code, String name) {
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
