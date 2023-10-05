package id.co.mii.serverapp.models.dto;

import java.util.List;

import id.co.mii.serverapp.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;

    private List<EmployeeDTO> employees;

}
