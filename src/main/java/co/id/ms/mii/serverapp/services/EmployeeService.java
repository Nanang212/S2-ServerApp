package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.dto.request.EmployeeRequest;
import co.id.ms.mii.serverapp.models.Country;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.models.User;
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
    private UserService userService;

    public List<Employee> getall(){
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id){
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id Employee is not found!!!")
        );
    }

    public Employee create (EmployeeRequest employeeRequest){
        if(employeeRepository.existsByName(employeeRequest.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Name Employee already exists!!!");
        }

        if (employeeRepository.existsByEmail(employeeRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email Employee already exists!!!");
        }

        User findUserId = userService.getById(employeeRequest.getUserid());

        if (employeeRepository.existsByUser(findUserId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee is already associated with a User");
        }

        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setUser(findUserId);

        return employeeRepository.save(employee);
    }

    public Employee update(EmployeeRequest employeeRequest,Integer id){
        if(employeeRepository.existsByName(employeeRequest.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Name Employee already exists!!!");
        }

        if (employeeRepository.existsByEmail(employeeRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email Employee already exists!!!");
        }

        User findUserId = userService.getById(employeeRequest.getUserid());

        if (employeeRepository.existsByUser(findUserId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee is already associated with a User");
        }

        Employee employee = getById(id);
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setUser(findUserId);

        return employeeRepository.save(employee);
    }

    public Employee delete(Integer id) {
        Employee user = getById(id);
        employeeRepository.delete(user);
        return user;
    }
}
