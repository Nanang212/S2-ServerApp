package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class EmployeeController {
  private EmployeeService employeeService;

  @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
  @GetMapping
  public ResponseEntity<List<Employee>> getAll() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(employeeService.getAll());
  }

  @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
  @GetMapping("/{id}")
  public ResponseEntity<Employee> getById(@PathVariable Integer id) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(employeeService.getById(id));
  }

  @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
  @GetMapping("/current")
  public ResponseEntity<Employee> getLoggedInEmployee() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(employeeService.getLoggedInEmployee());
  }

  @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody RegistrationRequest registrationRequest) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(employeeService.update(id, registrationRequest));
  }

  @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN', 'UPDATE_USER')")
  @PutMapping("/profile/{id}")
  public ResponseEntity<Employee> editProfile(@PathVariable Integer id, @RequestBody RegistrationRequest registrationRequest) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(employeeService.update(id, registrationRequest));
  }

  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Employee> delete(@PathVariable Integer id) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(employeeService.delete(id));
  }
}
