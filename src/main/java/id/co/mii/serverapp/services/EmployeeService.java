package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserEmployeeRequest;
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
    private EmployeRepo employeRepo;
    private ModelMapper modelMapper;
    public Employee create(UserEmployeeRequest userEmployeeRequest) {
        if (userRepo.existsByUsername(userEmployeeRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used !!!");
        }
        if (employeRepo.existsByEmail(userEmployeeRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
        }
        if (employeRepo.existsByPhone(userEmployeeRequest.getPhone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
        }
        Employee employee = modelMapper.map(userEmployeeRequest, Employee.class);
        User user = modelMapper.map(userEmployeeRequest, User.class);
        employee.setUser(user);
        user.setEmployee(employee);
        userRepo.save(user);
        employeRepo.save(employee);
        return employee;
    }
    public Employee update(Integer id, UserEmployeeRequest userEmployeeRequest) {
        Employee updatedEmployee = getById(id);
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getUsername())
                && !updatedEmployee.getUser().getUsername().equalsIgnoreCase(userEmployeeRequest.getUsername())) {
            if (userRepo.existsByUsername(userEmployeeRequest.getUsername())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used !!!");
            }
            updatedEmployee.getUser().setUsername(userEmployeeRequest.getUsername());
        }
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getPassword())
                && !updatedEmployee.getUser().getPassword().equalsIgnoreCase(userEmployeeRequest.getPassword())) {
            updatedEmployee.getUser().setPassword(userEmployeeRequest.getPassword());
        }
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getName())
                && !updatedEmployee.getName().equalsIgnoreCase(userEmployeeRequest.getName())) {
            updatedEmployee.setName(userEmployeeRequest.getName());
        }
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getEmail())
                && !updatedEmployee.getEmail().equalsIgnoreCase(userEmployeeRequest.getEmail())) {
            if (employeRepo.existsByEmail(userEmployeeRequest.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
            }
            updatedEmployee.setEmail(userEmployeeRequest.getEmail());
        }
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getPhone())
                && !updatedEmployee.getPhone().equalsIgnoreCase(userEmployeeRequest.getPhone())) {
            if (employeRepo.existsByPhone(userEmployeeRequest.getPhone())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
            }
            updatedEmployee.setPhone(userEmployeeRequest.getPhone());
        }
        return employeRepo.save(updatedEmployee);
    }

    @Override
    public Employee delete(Integer id) {
        
        return super.delete(id);
    }
}
