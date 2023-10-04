package id.co.mii.serverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserEmployeeRequest;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private EmployeeService employeeService;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    // public List<User> getAll(){
    // return userRepository.find
    // }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak ada id: " + id));
    }

    public User create(User user) {
        // if(userRepository.existsByEmployeeId(user.getEmployee().getId())){
        // throw new ResponseStatusException(HttpStatus.CONFLICT, "employee id must be
        // unique");
        // }
        return userRepository.save(user);
    }

    public User update(User user, Integer id) {
        getById(id);
        user.setId(id);
        return create(user);
    }

    public User delete(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }

    public User createUserEmployee(UserEmployeeRequest request) {
        User user = modelMapper.map(request, User.class);

        Employee employee = modelMapper.map(request, Employee.class);

        user.setEmployee(employee);
        employee.setUser(user);

        create(user);
        employeeService.create(employee);

        return user;
    }

}
