package id.co.mii.serverapp.services;

import java.util.List;

// import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.EmployeeRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private UserService userService;
    private UserRepository userRepository;

    public Employee findByToken(String token) {
        return employeeRepository
                .findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }
    
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee"));
    }

    public Employee update(Integer id, EmployeeRequest employeeRequest){

        if (employeeRepository.existsByName(employeeRequest.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name employee already taken");
        }

        if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email employee already taken");
        }

        if (employeeRepository.existsByPhone(employeeRequest.getPhone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone employee already taken");
        }

        Employee employee = getById(id);
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        return employeeRepository.save(employee);
    }

    public Employee delete(Integer id){
        User user = userService.getById(id);
        user.setEmployee(null);
        userRepository.save(user);

        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
