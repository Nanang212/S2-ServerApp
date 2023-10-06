package id.co.mii.serverapp.models.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryRequest {
    @NotNull
    @Positive
    private Integer regionId;
    @NotNull
    @Size(min = 2, max = 2)
    private String code;
    @NotNull
    @Size(min = 1, max = 64)
    private String name;
}
