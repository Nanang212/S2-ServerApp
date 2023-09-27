package id.co.mii.serverapp.models;

import java.util.List;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //apa yang membedakan data dari country ke region , apakah sama untuk pemanggiilan lombok?
@AllArgsConstructor
@NoArgsConstructor //pokoknya tidak boleh ada yang duplicate methodnya 
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //untuk apa didalm region ini perlu menggunakan jsonProperty?apakah harus diakses
    private List<Country>country;
    


    // public Region() {
    // }

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

