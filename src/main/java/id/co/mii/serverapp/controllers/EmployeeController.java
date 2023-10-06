package id.co.mii.serverapp.controllers;

import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.dto.Employee;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class EmployeeController {

  private EmployeeService employeeService;

  @GetMapping
  @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
  public List<Employee> getAll() {
    return employeeService.getAll();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('READ_ADMIN')")
  public Employee getById(@PathVariable Integer id) {
    return employeeService.getById(id);
  }
  @PostMapping
  public Employee create(@RequestBody Employee employee){
    return employeeService.create(employee);
  }

  @PutMapping("/{id}")
  public Employee update(
    @PathVariable Integer id,
    @RequestBody Employee employee
  ) {
    return employeeService.update(id, employee);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
  public Employee delete(@PathVariable Integer id) {
    return employeeService.delete(id);
  }
}