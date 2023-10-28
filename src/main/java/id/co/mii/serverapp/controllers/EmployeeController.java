package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.services.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class EmployeeController {

  private EmployeeService employeeService;

  @GetMapping
  // @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
  public List<Employee> getAll() {
    return employeeService.getAll();
  }

  // @PostMapping("/registration")
  // public Employee create(@RequestBody RegistrationRequest registrationRequest) {
  //   return employeeService.create(registrationRequest);
  // }

  // @PutMapping("/{id}")
  // public Employee update(
  //     @PathVariable Integer id,
  //     @RequestBody RegistrationRequest registrationRequest) {
  //   return employeeService.update(id, registrationRequest);
  // }

  @GetMapping("/{id}")
  // @PreAuthorize("hasAuthority('READ_ADMIN')")
  public Employee getById(@PathVariable Integer id) {
    return employeeService.getById(id);
  }

  @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody RegistrationRequest registrationRequest) {
        return employeeService.update(id, registrationRequest);
    }

  @DeleteMapping("/{id}")
  // @PreAuthorize("hasAuthority('DELETE_ADMIN')")
  public Employee delete(@PathVariable Integer id) {
    return employeeService.delete(id);
  }
}
