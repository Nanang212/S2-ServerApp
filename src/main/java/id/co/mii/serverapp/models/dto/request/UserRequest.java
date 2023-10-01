package id.co.mii.serverapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    @NotNull
    @Size(min = 4, max = 32)
    private String username;
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
    @NotNull
    private Set<@Positive Integer> roleIds;
}
