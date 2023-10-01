package id.co.mii.serverapp.models.dto;

public class CountryRequest {
    private String code;
    private String name;
    private Integer regionId;


    public CountryRequest(String code, String name, Integer regionId) {
        this.code = code;
        this.name = name;
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


    public Integer getRegionId() {
        return regionId;
    }


    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    
    
}

