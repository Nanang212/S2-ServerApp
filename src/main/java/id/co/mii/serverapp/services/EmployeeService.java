package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    // private UserService userService;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee create(Employee employee) {
        if (employee.getName() == null || employee.getName().isEmpty() ||
            employee.getEmail() == null || employee.getEmail().isEmpty() ||
            employee.getPhone() == null || employee.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Name, email, and phone are required fields.");
        }
        return employeeRepository.save(employee);
    }

    public Employee update(Integer id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setPhone(updatedEmployee.getPhone());
            return employeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
