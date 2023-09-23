package com.bagus2x.serverapp.region.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegionCreationDto {
    @NotNull
    @Size(min = 1, max = 64)
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
