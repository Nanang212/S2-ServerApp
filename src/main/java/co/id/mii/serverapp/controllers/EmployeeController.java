package co.id.mii.serverapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.mii.serverapp.models.Employee;
import co.id.mii.serverapp.models.dto.request.EmployeeRequest;
import co.id.mii.serverapp.services.EmployeeServices;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class EmployeeController {
    
    private EmployeeServices employeeServices;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Employee> getAll() {
        return employeeServices.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public Employee getById(@PathVariable Integer id) {
        return employeeServices.getById(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeServices.update(id,employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    public Employee delete(@PathVariable Integer id) {
        return employeeServices.delete(id);
    }

    
}
