package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.dto.request.EmployeeRequest;
import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.models.Country;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private UserService userService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public List<Employee> getall(){
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id){
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id Employee is not found!!!")
        );
    }

    public Employee getByUserToken(String token){
        return employeeRepository.findByUserToken(token).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Employee update(Integer id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee update(Integer id, UserRequest userRequest) {
        Employee employeefind = getById(id);
        User user = userService.getById(id);
        employeefind.setName(userRequest.getName());
        employeefind.setEmail(userRequest.getEmail());
        employeefind.setPhone(userRequest.getPhone());

        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        employeefind.setUser(user);
        user.setEmployee(employeefind);

        return employeeRepository.save(employeefind);
    }

    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

//    public Employee create (EmployeeRequest employeeRequest){
//        //employee name validation
//        if(employeeRequest.getName().isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee Name Empty!!!");
//        }
//
//        if(employeeRepository.existsByNameIgnoreCase(employeeRequest.getName())){
//            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Name already exists!!!");
//        }
//
//        //employee email validation
//        if(employeeRequest.getEmail().isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee Email Empty!!!");
//        }
//
//        if (employeeRepository.existsByEmail(employeeRequest.getEmail())){
//            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Email already exists!!!");
//        }
//
//        //employee phone validation
//        if(employeeRequest.getPhone().isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee Phone is Empty!!!");
//        }
//
//        if (employeeRepository.existsByPhone(employeeRequest.getPhone())){
//            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Phone already exists!!!");
//        }
//
//        //check if user is already associated with employee
//        User findUserId = userService.getById(employeeRequest.getUserid());
//        if (employeeRepository.existsByUser(findUserId)){
//            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee is already associated with a User");
//        }
//
//        Employee employee = new Employee();
//        employee.setName(employeeRequest.getName());
//        employee.setEmail(employeeRequest.getEmail());
//        employee.setPhone(employeeRequest.getPhone());
//        employee.setUser(findUserId);
//
//        return employeeRepository.save(employee);
//    }
}
