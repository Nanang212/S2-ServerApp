package id.co.mii.serverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private UserService userService;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee id not found"));
    }


    public Employee update(Integer id, Employee employee){
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee delete(Integer id){
        User user = userService.getById(id);
        user.getRoles().forEach(role-> role.getUsers().remove(user));
        userRepository.delete(user);

        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    public Employee updatingUser(RegistrationRequest registrationRequest , Integer id){
        User user = modelMapper.map(registrationRequest, User.class);
        Employee employee = modelMapper.map(registrationRequest, Employee.class);

        
        return null;
    }

}
