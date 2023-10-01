package id.co.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer id;

    @Column(name = "region_name",nullable = false, length = 20, unique = true)
    private String name;

    @OneToMany(mappedBy = "region")// diambil dari penamaan pada country (region).
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<Country> countries;

}