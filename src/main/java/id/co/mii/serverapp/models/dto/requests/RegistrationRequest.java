
package id.co.mii.serverapp.models.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    
  private String name;
  private String email;
  private String phone;
  private String username;
  private String password;
}
