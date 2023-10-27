package id.co.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.mii.serverapp.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "regions")
public class Region extends BaseEntity {
  @Column(nullable = false, length = 20, unique = true)
  private String name;

  @OneToMany(mappedBy = "region", cascade = CascadeType.REMOVE)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Country> countries;
}
