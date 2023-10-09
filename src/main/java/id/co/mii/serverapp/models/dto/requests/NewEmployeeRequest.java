package id.co.mii.serverapp.models.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewEmployeeRequest {

    @NotNull
    @Size(min = 4, max = 32)
    private String name;
    @NotNull
    @Email
    private String email;
}
