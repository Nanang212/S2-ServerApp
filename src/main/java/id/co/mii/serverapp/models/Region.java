package id.co.mii.serverapp.models;

import java.util.List;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "regions")

public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")

    private Integer id;

    @Column (name = "region_name", nullable = true, length = 20)
    private String name;

    @OneToMany(mappedBy = "region")  
    private List<Country>country;
    


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
    //variable digunakan untuk penampung
    //nilai 0

}    

