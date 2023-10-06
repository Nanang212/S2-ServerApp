package id.co.mii.serverapp.services;

import java.util.List;

//import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
//import id.co.mii.serverapp.models.User;
//import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
//import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    // private ModelMapper modelMapper;
    // private UserRepository userRepository;
    // private UserService userService;

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }
    
    public Employee getById(Integer id){
        return employeeRepository
        .findById(id)
        .orElseThrow(() -> new
        ResponseStatusException(HttpStatus.NOT_FOUND,
        "Employee not found !!!"));
    }

    // public Employee create(EmployeeRequest employeeRequest){
        
    //     if(employeeRepository.existsByName(employeeRequest.getName())){
    //         throw new ResponseStatusException(HttpStatus.CONFLICT,"Name Employee already exists!!!");
    //     }
    //     if (employeeRepository.existsByEmail(employeeRequest.getEmail())){
    //         throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Email already exists!!!");
    //     }
    //     if (employeeRepository.existsByPhone(employeeRequest.getPhone())){
    //         throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Phone already exists!!!");
    //     }

    //     Employee employee = modelMapper.map(employeeRequest, Employee.class);
    //     User user = userService.getById(employeeRequest.getUserId());
    //     employee.setUser(user);
    //     user.setEmployee(employee);
    //     userRepository.save(user);
    //     return employee;
    // }

    public Employee update(Integer id, Employee employee){

        if(employeeRepository.existsByName(employee.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Name Employee already exists!!!");
        }
        if (employeeRepository.existsByEmail(employee.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Email already exists!!!");
        }
        if (employeeRepository.existsByPhone(employee.getPhone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Phone already exists!!!");
        }
        
        Employee updateEmployee = getById(id);
        updateEmployee.setName(employee.getName());
        updateEmployee.setEmail(employee.getEmail());
        updateEmployee.setPhone(employee.getPhone());
        return employeeRepository.save(updateEmployee);
    }

    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
