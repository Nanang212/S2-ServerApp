package id.co.mii.serverapp.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Column(name = "region_name", 
          nullable = false, 
          length = 20)
  private String name;

  @OneToMany(mappedBy = "region")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Country> countries;
}
