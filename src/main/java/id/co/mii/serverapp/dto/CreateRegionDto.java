package id.co.mii.serverapp.dto;

public class CreateRegionDto {
    private String name;

    public CreateRegionDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
