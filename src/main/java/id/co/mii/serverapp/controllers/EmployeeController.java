package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(
        value = "/employee",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody EmployeeRequest request) {
        return employeeService.create(request);
    }

    @GetMapping(
        value = "/employee/{employeeId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee getById(@PathVariable Integer employeeId) {
        return employeeService.getById(employeeId);
    }

    @GetMapping(
        value = "/employees",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @PutMapping(
        value = "/employee/{employeeId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee create(@PathVariable Integer employeeId, @RequestBody EmployeeRequest request) {
        return employeeService.update(employeeId, request);
    }

    @DeleteMapping(
        value = "/employee/{employeeId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee delete(@PathVariable Integer employeeId) {
        return employeeService.delete(employeeId);
    }
}
