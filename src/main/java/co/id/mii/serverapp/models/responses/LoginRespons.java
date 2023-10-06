package co.id.mii.serverapp.models.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRespons {
    private String username;
    private String email;
    private List<String> authorities;
}
