package id.co.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.mii.serverapp.models.base.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {
    @Column(length = 2, unique = true)
    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Region region;

    public Country() {
    }

    public Country(Integer id, String code, String name, Region region) {
        super(id);
        this.code = code;
        this.name = name;
        this.region = region;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
