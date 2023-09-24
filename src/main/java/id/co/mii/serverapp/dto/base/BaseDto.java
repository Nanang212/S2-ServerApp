package id.co.mii.serverapp.dto.base;

public class BaseDto {
    private String name;

    public BaseDto() {
    }

    public BaseDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
