package co.id.mii.serverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.id.mii.serverapp.models.Employee;
import co.id.mii.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServices {
    private EmployeeRepository employeeRepository;
    
    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id){
        return employeeRepository
        .findById(id)
        .orElseThrow(
            () ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!!")
        );
    }
    public Employee update(Integer id, Employee employee){
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }
    public Employee delete(Integer id){
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
