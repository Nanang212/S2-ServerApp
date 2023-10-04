package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private UserRepository userRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak ada id: " + id));
    }

    public Employee create(Employee employee) {
        // if(employeeRepository.existsByUserId(employee.getUser().getId())){
        // throw new ResponseStatusException(HttpStatus.CONFLICT, "user id must be
        // unique");
        // }
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee, Integer id) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    public Employee createEmployeByExistingUser(Employee employee, Integer id) {
        User user = userRepository.findById(id).orElse(null);

        if (employeeRepository.existsByUserId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user id is already exist");
        }

        // user.setEmployee(employee);
        employee.setUser(user);
        return employeeRepository.save(employee);

    }
}
