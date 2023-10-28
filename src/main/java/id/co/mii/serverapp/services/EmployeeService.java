package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class EmployeeService {

  private EmployeeRepository employeeRepository;
  private BCryptPasswordEncoder passwordEncoder;

  public Optional<Employee> findByUuid(String uuid) {
    return employeeRepository.findByUuid(uuid);
  }

  public List<Employee> getAll() {
    return employeeRepository.findAll();
  }

  public Employee getById(Integer id) {
    return employeeRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Employee not found!!!"));
  }

  // public Employee create(RegistrationRequest registrationRequest) {

  // Employee employee = new Employee();
  // employee.setName(registrationRequest.getName());
  // employee.setEmail(registrationRequest.getEmail());
  // employee.setPhone(registrationRequest.getPhone());

  // User user = new User();
  // user.setUsername(registrationRequest.getUsername());
  // user.setPassword(registrationRequest.getPassword());

  // List<Role> roles = Collections.singletonList(roleService.getById(2));
  // user.setRoles(roles);

  // employee.setUser(user);
  // user.setEmployee(employee);

  // return employeeRepository.save(employee);
  // }

  // public Employee update(Integer id, RegistrationRequest registrationRequest) {
  // Employee employee = getById(id);
  // User user = userService.getById(id);

  // employee.setName(registrationRequest.getName());
  // employee.setEmail(registrationRequest.getEmail());
  // employee.setPhone(registrationRequest.getPhone());

  // user.setUsername(registrationRequest.getUsername());
  // user.setPassword(registrationRequest.getPassword());

  // employee.setUser(user);
  // user.setEmployee(employee);

  // return employeeRepository.save(employee);
  // }

  public Employee update(Integer id, RegistrationRequest registrationRequest) {

    if (employeeRepository.existsByName(registrationRequest.getName())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Name employee already taken");
    }

    if (employeeRepository.existsByEmail(registrationRequest.getEmail())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email employee already taken");
    }

    if (employeeRepository.existsByPhone(registrationRequest.getPhone())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone employee already taken");
    }

    Employee employee = getById(id);
    employee.getUser().setIsEnabled(true);
    employee.getUser().setUsername(registrationRequest.getUsername());
    employee.setUuid("");
    employee.getUser().setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
    employee.setPhone(registrationRequest.getPhone());
    return employeeRepository.save(employee);
  }

  public Employee delete(Integer id) {
    Employee employee = getById(id);
    employeeRepository.delete(employee);
    return employee;
  }
}
