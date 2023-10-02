package id.co.mii.serverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.request.EmployeeRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private UserService userService;
    private ModelMapper modelMapper;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak ditemukan id no :" + id));
    }

    public Employee insertDTOByModelMapper(EmployeeRequest employeeRequest){
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        employee.setUser(userService.getById(employeeRequest.getUserId()));
        return employeeRepository.save(employee);
    }

    public Employee update(Integer id, Employee employee){
        getById(id);
        employee.setId(id);
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Sudah Tersedia");
        }
        if (employeeRepository.existsByPhone(employee.getPhone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone sudah tersedia");
        }

        return employeeRepository.save(employee);
    }

    public Employee delete(Integer id){
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
