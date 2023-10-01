package co.id.mii.serverapp.controllers;

import java.util.List;

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

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    
    private EmployeeServices employeeServices;

    @GetMapping
    public List<Employee> getAll() {
        return employeeServices.getall();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id) {
        return employeeServices.getById(id);
    }

    @PostMapping
    public Employee create(@RequestBody EmployeeRequest EmployeeRequest) {
        return employeeServices.create(EmployeeRequest);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeServices.update(employeeRequest, id);
    }

    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable Integer id) {
        return employeeServices.delete(id);
    }

    
}
