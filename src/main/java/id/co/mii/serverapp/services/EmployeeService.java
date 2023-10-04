package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;
import id.co.mii.serverapp.repositories.EmployeRepo;
import id.co.mii.serverapp.repositories.UserRepo;
import id.co.mii.serverapp.services.base.BaseService;
import id.co.mii.serverapp.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class EmployeeService extends BaseService<Employee> {
    private UserRepo userRepo;
    private UserService userService;
    private EmployeRepo employeRepo;
    private ModelMapper modelMapper;

    public Employee create(EmployeeRequest employeeRequest) {
        if (employeRepo.existsByEmail(employeeRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
        }
        if (employeRepo.existsByPhone(employeeRequest.getPhone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
        }
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        User user = userService.getById(employeeRequest.getUserId());
        employee.setUser(user);
        user.setEmployee(employee);
        userRepo.save(user);
        return employee;
    }

    public Employee update(Integer id, EmployeeRequest employeeRequest) {
        Employee updatedEmployee = getById(id);
        if (!StringUtils.isEmptyOrNull(employeeRequest.getName())
                && !updatedEmployee.getName().equalsIgnoreCase(employeeRequest.getName())) {
            updatedEmployee.setName(employeeRequest.getName());
        }
        if (!StringUtils.isEmptyOrNull(employeeRequest.getEmail())
                && !updatedEmployee.getEmail().equalsIgnoreCase(employeeRequest.getEmail())) {
            if (employeRepo.existsByEmail(employeeRequest.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
            }
            updatedEmployee.setEmail(employeeRequest.getEmail());
        }
        if (!StringUtils.isEmptyOrNull(employeeRequest.getPhone())
                && !updatedEmployee.getPhone().equalsIgnoreCase(employeeRequest.getPhone())) {
            if (employeRepo.existsByPhone(employeeRequest.getPhone())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
            }
            updatedEmployee.setPhone(employeeRequest.getPhone());
        }
        return employeRepo.save(updatedEmployee);
    }

    @Override
    public Employee delete(Integer id) {
        User user = userService.getById(id);
        user.setEmployee(null);
        userRepo.save(user);

        Employee employee = getById(id);
        employeRepo.delete(employee);
        return employee;
    }
}
