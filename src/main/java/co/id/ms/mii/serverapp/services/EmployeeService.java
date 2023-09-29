package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.models.Country;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public List<Employee> getall(){
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id){
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id Employee is not found!!!")
        );
    }

    public Employee create (Employee employee){
        if(employeeRepository.existsByName(employee.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Name Employee already exists!!!");
        }

        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee,Integer id){
        if(employeeRepository.existsByName(employee.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Name Employee already exists!!!");
        }
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee delete(Integer id) {
        Employee user = getById(id);
        employeeRepository.delete(user);
        return user;
    }
}
