package co.id.ms.mii.serverapp.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {
    private String code;
    private String name;
    private Integer region_id;
}
