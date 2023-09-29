package id.co.mii.serverapp.models.dto.requests;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {

  private String code;
  private String name;
  private Integer regionId;
}
