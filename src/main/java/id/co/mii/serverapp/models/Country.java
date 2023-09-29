package id.co.mii.serverapp.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_country")
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "country_id")
  private Integer id;

  @Column(name = "country_code", 
         length = 2,
         nullable = false, 
         unique = true)
  private String code;

  @Column(name = "country_name", 
          nullable = false, 
          length = 20)
  private String name;

  @ManyToOne
  @JoinColumn(name = "region_id", 
              nullable = false)

  private Region region;
}
