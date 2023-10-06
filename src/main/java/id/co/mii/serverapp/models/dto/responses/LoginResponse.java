package id.co.mii.serverapp.models.dto.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String password;
    private List<String> authorities;
}
