package id.co.mii.serverapp.models.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
  private String name;
  private String email;
  private String phone;
  private String username;
}
