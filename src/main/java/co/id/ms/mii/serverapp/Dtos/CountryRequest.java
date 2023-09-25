package co.id.ms.mii.serverapp.Dtos;

public class CountryRequest {
    private String code;
    private String name;
    private Integer region_id;

    public CountryRequest(String code, String name, Integer region_id) {
        this.code = code;
        this.name = name;
        this.region_id = region_id;
    }

    public CountryRequest() {
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

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }
}
