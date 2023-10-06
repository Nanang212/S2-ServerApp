package co.id.ms.mii.serverapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String email;
    private List<String> authorities;
}
