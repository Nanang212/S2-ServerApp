package id.co.mii.serverapp.models.dto.respones;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponse {
    
    public String username;
    
    public String email;
    
    public List<String> authorities;
}
