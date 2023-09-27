package id.co.mii.serverapp.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name =  "tb_county")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer id;

    @Column(length = 2)
    private String code;
    @Column(name = "country_name", nullable = false, length = 30, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    private Region region;

}
