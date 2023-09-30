package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.services.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

public class EmployeeController {
    
    private EmployeeService employeeService;

    

    @GetMapping("/employee")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getById(@PathVariable Integer id){
        return employeeService.getById(id);
    }

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@RequestBody Employee employee, @PathVariable Integer id){
        return employeeService.update(employee, id);
    }

    @DeleteMapping("/employee/{id}")
    public Employee delete(@PathVariable Integer id){
        return employeeService.delete(id);
    }

    @PostMapping("/users/{id}/employees")
    public Employee createEmployeByExistingUser(@RequestBody Employee employee, @PathVariable Integer id){
        return employeeService.createEmployeByExistingUser(employee, id);
    }
}
