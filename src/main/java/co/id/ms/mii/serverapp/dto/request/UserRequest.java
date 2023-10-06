package co.id.ms.mii.serverapp.dto.request;

import co.id.ms.mii.serverapp.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;

//    private List<Role> rolesname;
}
