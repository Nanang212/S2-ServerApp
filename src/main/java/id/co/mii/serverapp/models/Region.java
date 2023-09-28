package id.co.mii.serverapp.models;

import java.util.List;

import javax.persistence.CascadeType;
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

@Data
@Entity //membuat table baru di mysql
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_region") //merename nama table
public class Region {

    @Id //menunjukkan primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "region_id")
    private Integer id;

    @Column(name = "region_name", nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "region",cascade = CascadeType.REMOVE )//kalau cascade akan menghapus child jika parent dihapus
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //memetakan objek java ke json dan sebaliknya
    private List<Country> countries;
}
