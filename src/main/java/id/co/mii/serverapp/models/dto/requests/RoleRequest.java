package id.co.mii.serverapp.models.dto.requests;

import java.util.List;
import id.co.mii.serverapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {

    private String name;
    private List<User> users;
}
