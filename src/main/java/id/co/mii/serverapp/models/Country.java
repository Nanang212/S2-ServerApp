package id.co.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.mii.serverapp.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends BaseEntity {
  @Column(length = 2, unique = true)
  private String code;

  private String name;

  @ManyToOne
  @JoinColumn(name = "region_id")
  private Region region;
}
