package id.co.mii.serverapp.models.dto.requests;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    // private List<Integer> rolesId;
}
