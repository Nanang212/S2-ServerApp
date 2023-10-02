package id.co.mii.serverapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    //sebuah anotasi yang dari spring boot
    private EmployeeService employeeService;

    // Create Employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // Read Employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Read All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Update Employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
       return employeeService.updatEmployee(id, employee);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);

    }
    @PostMapping("/users/{id}/employees")
    public Employee createUserEmployee( @RequestBody Employee employee , @PathVariable Long id){
        return employeeService.createUserEmployee(employee, id);
        //apa hibungan user dengan employee?
        //kenapa ketika mencreate employe data nya bisa,  cuman diuser didalam employee null?
    }


}
