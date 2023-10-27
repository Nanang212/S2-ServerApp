package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.repositories.EmployeRepo;
import id.co.mii.serverapp.repositories.UserRepo;
import id.co.mii.serverapp.services.base.BaseService;
import id.co.mii.serverapp.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService extends BaseService<Employee> {
  private EmployeRepo employeRepo;
  private UserRepo userRepo;
  private PasswordEncoder passwordEncoder;
  private RoleService roleService;

  public Employee update(Integer id, RegistrationRequest registrationRequest) {
    Employee updatedEmployee = getById(id);
    if (updatedEmployee.getName() == null) {
      updatedEmployee.setName(registrationRequest.getName());
    } else {
      if (!StringUtils.isEmptyOrNull(registrationRequest.getName())
              && !updatedEmployee.getName().equalsIgnoreCase(registrationRequest.getName())) {
        updatedEmployee.setName(registrationRequest.getName());
      }
    }

    if (updatedEmployee.getEmail() == null) {
      if (employeRepo.existsByEmail(registrationRequest.getEmail())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
      }
      updatedEmployee.setEmail(registrationRequest.getEmail());
    } else {
      if (!StringUtils.isEmptyOrNull(registrationRequest.getEmail())
              && !updatedEmployee.getEmail().equalsIgnoreCase(registrationRequest.getEmail())) {
        if (employeRepo.existsByEmail(registrationRequest.getEmail())) {
          throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
        }
        updatedEmployee.setEmail(registrationRequest.getEmail());
      }
    }

    if (updatedEmployee.getPhone() == null) {
      if (employeRepo.existsByPhone(registrationRequest.getPhone())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
      }
      updatedEmployee.setPhone(registrationRequest.getPhone());
    } else {
      if (!StringUtils.isEmptyOrNull(registrationRequest.getPhone())
              && !updatedEmployee.getPhone().equalsIgnoreCase(registrationRequest.getPhone())) {
        if (employeRepo.existsByPhone(registrationRequest.getPhone())) {
          throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
        }
        updatedEmployee.setPhone(registrationRequest.getPhone());
      }
    }

    if (updatedEmployee.getUser().getUsername() == null) {
      if (userRepo.existsByUsername(registrationRequest.getUsername())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used !!!");
      }
      updatedEmployee.getUser().setUsername(registrationRequest.getUsername());
    } else {
      if (!StringUtils.isEmptyOrNull(registrationRequest.getUsername())
              && !updatedEmployee.getUser().getUsername().equalsIgnoreCase(registrationRequest.getUsername())) {
        if (userRepo.existsByUsername(registrationRequest.getUsername())) {
          throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used !!!");
        }
        updatedEmployee.getUser().setUsername(registrationRequest.getUsername());
      }
    }

    if (updatedEmployee.getUser().getPassword() != null) {
      if (!StringUtils.isEmptyOrNull(registrationRequest.getPassword())
              && !updatedEmployee.getUser().getPassword().equalsIgnoreCase(registrationRequest.getPassword())) {
        updatedEmployee.getUser().setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
      }
    } else {
      updatedEmployee.getUser().setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
    }

    if (registrationRequest.getRoleIds() != null) {
      updatedEmployee.getUser().setRoles(mapToRoles(registrationRequest.getRoleIds()));
    }
    updatedEmployee.getUser().setIsEnable(true);
    updatedEmployee.getUser().setToken(null);
    return employeRepo.save(updatedEmployee);
  }

  @Override
  public Employee delete(Integer id) {
    Employee employee = getById(id);
    employeRepo.delete(employee);
    return employee;
  }

  private List<Role> mapToRoles(List<Integer> roleIds) {
    return roleIds
            .stream()
            .map(roleId -> roleService.getById(roleId))
            .collect(Collectors.toList());
  }
}
