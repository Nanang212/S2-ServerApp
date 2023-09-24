package id.co.mii.serverapp.dto;

public class UpdateCountryDto {
    private String name;

    public UpdateCountryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
