package id.co.mii.serverapp.services;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;

import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private UserService userService;
    private ModelMapper modelMapper;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    // create with dto model mapper
    public Employee create(EmployeeRequest employeeRequest) {
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        User user = userService.getById(employeeRequest.getUserId());
        employee.setUser(user);
        user.setEmployee(employee);
        userRepository.save(user);
        return employee;
    }

    // update
    public Employee update(Integer id, EmployeeRequest employeeRequest) {
        // find id
        Employee updatedEmployee = getById(id);
        // set data
        updatedEmployee.setName(employeeRequest.getName());
        updatedEmployee.setMail(employeeRequest.getMail());
        updatedEmployee.setPhone(employeeRequest.getPhone());
        User user = userService.getById(employeeRequest.getUserId());
        updatedEmployee.setUser(user);
        user.setEmployee(updatedEmployee);
        userRepository.save(user);
        if (updatedEmployee.getName() != employeeRequest.getName()) {
            if (employeeRepository.existsByName(updatedEmployee.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already exists!");
            }
        }
        return updatedEmployee;
    }

    // seach by name
    // JPQL
    public List<Employee> search(String name) {
        if (employeeRepository.searchAllName("%" + name + "%").isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee does not exists!!!");
        }
        return employeeRepository.searchAllName("%" + name + "%");
    }

    // delete
    public Employee delete(Integer id) {
        User user = userService.getById(id);
        user.setEmployee(null);
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
