package co.id.ms.mii.serverapp.models;

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
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//rename table
@Table(name = "regions")
public class Region{

    @Id
    //generate constraint
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //rename column
    @Column(name = "region_id")
    private Integer id;
    
    @Column(name = "region_name",length = 50,columnDefinition = "varchar(50)")
    private String name;

    @OneToMany(mappedBy = "region")
                @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Country> country;
}
