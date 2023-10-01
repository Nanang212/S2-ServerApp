package id.co.mii.serverapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeRequest {
    private String name;
    private String email;
    private String phone;
    private Integer userId;
}
