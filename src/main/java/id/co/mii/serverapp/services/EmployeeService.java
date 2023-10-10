package id.co.mii.serverapp.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;
import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
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
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public Employee update(Integer id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    // update emplotee

     public Employee update(Integer id, RegistrationRequest registrationRequest) {

        Employee employee = getById(id);
        User user = employee.getUser();
        if (registrationRequest.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username harus diisi");
        }
        if (registrationRequest.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password harus diisi");
        }
        if (registrationRequest.getPhone().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone harus diisi");
        }
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.getEmployee().setPhone(registrationRequest.getPhone());
        user.setIsEnabled(true);
        user.setToken(null);
        return employeeRepository.save(employee);
    }
}
