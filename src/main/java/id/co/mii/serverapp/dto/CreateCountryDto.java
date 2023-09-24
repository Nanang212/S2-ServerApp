package id.co.mii.serverapp.dto;

public class CreateCountryDto {
    private String name;

    public CreateCountryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
