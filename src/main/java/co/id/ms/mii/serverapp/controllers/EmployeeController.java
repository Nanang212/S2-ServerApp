package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.models.Country;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping(name = "employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getall(){
        return employeeService.getall();
    }

    @GetMapping("/{id}")
    public Employee getbyid(@PathVariable Integer id){
        return employeeService.getById(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @PutMapping
    public Employee update(@RequestBody Employee employee, @PathVariable Integer id){
        return employeeService.update(employee,id);
    }

    @DeleteMapping
    public Employee delete(@PathVariable Integer id){
        return employeeService.delete(id);
    }
}
