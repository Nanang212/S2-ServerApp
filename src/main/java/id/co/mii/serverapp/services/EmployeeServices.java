package id.co.mii.serverapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.EmployeeDTO;
import id.co.mii.serverapp.models.request.CreateEmployeeRequest;
import id.co.mii.serverapp.models.request.UpdateEmployeeRequest;
import id.co.mii.serverapp.repositories.EmployeeRepositories;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServices {
    private EmployeeRepositories employeeRepositories;

    public List<Employee> getAll() {
        // List<Employee> employees = employeeRepositories.findAll();
        // return employees.stream()
        // .map(employee -> {
        // EmployeeDTO employeeDTO = new EmployeeDTO();
        // BeanUtils.copyProperties(employee, employeeDTO);
        // return employeeDTO;
        // })
        // .collect(Collectors.toList());
        return employeeRepositories.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepositories.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employees with that id is not found!!"));
    }

    public EmployeeDTO create(CreateEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employeeRepositories.save(employee);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    // public Employee update(Integer id, Employee employee){
    // getById(id);
    // employee.setId(id);
    // return employeeRepositories.save(employee);
    // }

    public EmployeeDTO update(Integer id, UpdateEmployeeRequest request) {
        Employee employee = employeeRepositories.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employeeRepositories.save(employee);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    public void delete(Integer id) {
        Employee employee = employeeRepositories.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        employeeRepositories.delete(employee);
    }
}
