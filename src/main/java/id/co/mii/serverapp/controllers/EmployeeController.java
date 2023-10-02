package id.co.mii.serverapp.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.EmployeeDTO;
import id.co.mii.serverapp.models.request.CreateEmployeeRequest;
import id.co.mii.serverapp.models.request.UpdateEmployeeRequest;
import id.co.mii.serverapp.services.EmployeeServices;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeServices employeeServices;

    @GetMapping
    public List<EmployeeDTO> getAll(){
        return employeeServices.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id){
        return employeeServices.getById(id);
    }

    @PostMapping
    public EmployeeDTO create(@RequestBody CreateEmployeeRequest employee){
        return employeeServices.create(employee);
    }

    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable Integer id, @RequestBody UpdateEmployeeRequest employeeRequest){
        return employeeServices.update(id, employeeRequest);
    }

    // tak pake return soalnya employeeServices.delete adalah void
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
         employeeServices.delete(id);
    }
}
