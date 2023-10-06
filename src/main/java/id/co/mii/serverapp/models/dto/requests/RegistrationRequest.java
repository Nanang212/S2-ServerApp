package id.co.mii.serverapp.models.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotNull
    @Size(min = 4, max = 32)
    private String name;
    @Email
    private String email;
    @Size(min = 3, max = 16)
    private String phone;
    @NotNull
    @Size(min = 4, max = 32)
    private String username;
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
}