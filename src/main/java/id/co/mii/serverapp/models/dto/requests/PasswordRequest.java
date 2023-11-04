package id.co.mii.serverapp.models.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequest {
    @NotNull
    @Size(min = 8, max = 32)
    private String oldPassword;
    @NotNull
    @Size(min = 8, max = 32)
    private String newPassword;
    @NotNull
    @Size(min = 8, max = 32)
    private String confirmationPassword;
}
