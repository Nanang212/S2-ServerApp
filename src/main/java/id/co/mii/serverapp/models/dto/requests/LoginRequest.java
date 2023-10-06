package id.co.mii.serverapp.models.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {
    
    public String username;

    public String password;
}
