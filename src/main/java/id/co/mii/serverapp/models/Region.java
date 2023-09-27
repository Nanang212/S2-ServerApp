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
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_region")
public class Region {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "region_id")
    private Integer id;

    @Column(name = "region_name", nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Country> countries;

}
