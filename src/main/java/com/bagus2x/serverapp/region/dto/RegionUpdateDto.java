package com.bagus2x.serverapp.region.dto;

import javax.validation.constraints.NotBlank;

public class RegionUpdateDto {
    @NotBlank
    private String name;

    public RegionUpdateDto() {
    }

    public RegionUpdateDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
