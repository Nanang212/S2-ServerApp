package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.utils.ModelMapperUtil;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee create(EmployeeRequest employeeRequest) {
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        if (employeeRequest.getName() == null || employeeRequest.getName().isEmpty() ||
            employeeRequest.getEmail() == null || employeeRequest.getEmail().isEmpty() ||
            employeeRequest.getPhone() == null || employeeRequest.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Name, email, and phone are required fields.");
        }
        User user = userService.getById(employeeRequest.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User with the provided ID not found."));
        employee.setUser(user);
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

    public Optional<Employee> delete(Integer id) {
        Optional<Employee> employee = getById(id);
        employeeRepository.delete(employee.get());
        return employee;
    }
}
