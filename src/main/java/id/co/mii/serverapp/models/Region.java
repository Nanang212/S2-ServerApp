package id.co.mii.serverapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //membuat table baru di mysql
@Table(name = "tb_region") //merename nama table
public class Region {

    @Id //menunjukkan primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //untuk membuat auto increment id
    @Column(name = "region_id")
    private Integer id;

    @Column(name = "region_name", nullable = false, length = 20)
    private String name;

    public Region(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Region() {
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
        return "Region [name=" + name + "]";
    }

}
