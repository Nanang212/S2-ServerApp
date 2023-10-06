package id.co.mii.serverapp.models.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponse {

    private String username;
    private String email;
    private Set<String> authorities;
}