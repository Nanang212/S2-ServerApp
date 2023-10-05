package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.RegistrationRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private EmployeeRepository employeeRepository;
  private ModelMapper modelMapper;
  private RoleService roleService;
  private PasswordEncoder passwordEncoder;

  public Employee registration(RegistrationRequest registrationRequest) {
    Employee employee = modelMapper.map(registrationRequest, Employee.class);
    User user = modelMapper.map(registrationRequest, User.class);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // set default role
    List<Role> roles = Collections.singletonList(roleService.getById(2));
    // List<Role> roles = new ArrayList<>();
    // roles.add(roleService.getById(2));
    user.setRoles(roles);

    employee.setUser(user);
    user.setEmployee(employee);

    return employeeRepository.save(employee);
  }
}