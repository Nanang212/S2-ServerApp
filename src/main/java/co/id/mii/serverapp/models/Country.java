package co.id.mii.serverapp.models;

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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "tb_country")
public class Country {

    @Id
    //untuk auto increment @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (length = 2, nullable = false, unique = true)
    private String code;
    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    Region region;  

}
