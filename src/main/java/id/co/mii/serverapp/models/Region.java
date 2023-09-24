package id.co.mii.serverapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_region")
public class Region {

    // id dibawah dijadikan primaary key dengan cara menuliskan @Id
    @Id
    // generated value dibawah digunakan untuk auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer id;
    @Column(name = "name", unique = true)
    private String name;

    public Region() {
    }

    public Region(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + "]";
    }

    // @OneToMany
    // private List <County> countries;

}
