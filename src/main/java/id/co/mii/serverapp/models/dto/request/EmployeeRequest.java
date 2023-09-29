package id.co.mii.serverapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeRequest {

    @NotNull
    @Size(min = 4, max = 32)
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 3, max = 16)
    private String phone;
    @NotNull
    @Positive
    private Integer userId;
}
