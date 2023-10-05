package id.co.mii.serverapp.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.dto.Employee;
import id.co.mii.serverapp.models.dto.Role;
import id.co.mii.serverapp.models.dto.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

  private EmployeeRepository employeeRepository;
  private ModelMapper modelMapper;
  private RoleService roleService;

  public Employee registration(RegistrationRequest registrationRequest) {
    Employee employee = modelMapper.map(registrationRequest, Employee.class);
    User user = modelMapper.map(registrationRequest, User.class);

    // set default role
    List<Role> roles = Collections.singletonList(roleService.getById(2)); 
    // List<Role> roles = new ArrayList<>();
    // roles.add(roleService.getById(2));
     // user.setRoles(roles);

    employee.setUser(user);
    user.setEmployee(employee);

    return employeeRepository.save(employee);
  }
}

