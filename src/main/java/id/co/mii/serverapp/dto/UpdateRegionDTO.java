package id.co.mii.serverapp.dto;

public class UpdateRegionDTO {
    private String name;

    public UpdateRegionDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
