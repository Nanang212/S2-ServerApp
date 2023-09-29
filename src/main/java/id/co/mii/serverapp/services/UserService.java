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
public class UserService extends BaseService<User> {
    private ModelMapper modelMapper;
    private UserRepo userRepo;
    private EmployeRepo employeRepo;
    public User create(UserEmployeeRequest userEmployeeRequest) {
        if (userRepo.existsByUsername(userEmployeeRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used !!!");
        }
        if (employeRepo.existsByEmail(userEmployeeRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
        }
        if (employeRepo.existsByPhone(userEmployeeRequest.getPhone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
        }
        User user = modelMapper.map(userEmployeeRequest, User.class);
        Employee employee = modelMapper.map(userEmployeeRequest, Employee.class);
        user.setEmployee(employee);
        employee.setUser(user);
        userRepo.save(user);
        employeRepo.save(employee);
        return user;
    }
    public User update(Integer id, UserEmployeeRequest userEmployeeRequest) {
        User updatedUser = getById(id);
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getUsername())
                && !updatedUser.getUsername().equalsIgnoreCase(userEmployeeRequest.getUsername())) {
            if (userRepo.existsByUsername(userEmployeeRequest.getUsername())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used !!!");
            }
            updatedUser.setUsername(userEmployeeRequest.getUsername());
        }
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getPassword())
                && !updatedUser.getPassword().equalsIgnoreCase(userEmployeeRequest.getPassword())) {
            updatedUser.setPassword(userEmployeeRequest.getPassword());
        }
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getName())
                && !updatedUser.getEmployee().getName().equalsIgnoreCase(userEmployeeRequest.getName())) {
            updatedUser.getEmployee().setName(userEmployeeRequest.getName());
        }
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getEmail())
                && !updatedUser.getEmployee().getEmail().equalsIgnoreCase(userEmployeeRequest.getEmail())) {
            if (employeRepo.existsByEmail(userEmployeeRequest.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
            }
            updatedUser.getEmployee().setEmail(userEmployeeRequest.getEmail());
        }
        if (!StringUtils.isEmptyOrNull(userEmployeeRequest.getPhone())
                && !updatedUser.getEmployee().getPhone().equalsIgnoreCase(userEmployeeRequest.getPhone())) {
            if (employeRepo.existsByPhone(userEmployeeRequest.getPhone())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
            }
            updatedUser.getEmployee().setPhone(userEmployeeRequest.getPhone());
        }
        return userRepo.save(updatedUser);
    }
}
