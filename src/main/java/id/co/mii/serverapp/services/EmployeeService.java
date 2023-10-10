package id.co.mii.serverapp.services;

import java.util.List;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));
    }

    public Employee update(Integer id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee update(Integer id, RegistrationRequest registrationRequest) {
        Employee updatedEmployee = getById(id);
        if (registrationRequest.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be empty");
        }
        if (registrationRequest.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be empty");
        }
        if (registrationRequest.getPhone().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number cannot be empty");
        }
        updatedEmployee.getUser().setUsername(registrationRequest.getUsername());
        updatedEmployee.getUser().setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        updatedEmployee.setPhone(registrationRequest.getPhone());
        updatedEmployee.getUser().setIsEnabled(true);
        updatedEmployee.getUser().setToken(null);
        return employeeRepository.save(updatedEmployee);
    }
    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

}
