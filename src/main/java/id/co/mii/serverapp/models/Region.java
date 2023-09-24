package id.co.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.mii.serverapp.models.base.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region extends BaseEntity {
    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @OneToMany(mappedBy = "region")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    private List<Country> countries;

    public Region() {
    }

    public Region(Integer id, String name, List<Country> countries) {
        super(id);
        this.name = name;
        this.countries = countries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
