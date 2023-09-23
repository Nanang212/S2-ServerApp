package com.bagus2x.serverapp.region.dto;

import javax.validation.constraints.NotBlank;

public class RegionCreationDto {
    @NotBlank
    private String name;

    public RegionCreationDto() {
    }

    public RegionCreationDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
