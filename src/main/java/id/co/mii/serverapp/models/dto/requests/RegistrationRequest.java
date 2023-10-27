package id.co.mii.serverapp.models.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
  private String username;
  private String password;
  private String name;
  private String email;
  private String phone;
  private List<Integer> roleIds;
}
