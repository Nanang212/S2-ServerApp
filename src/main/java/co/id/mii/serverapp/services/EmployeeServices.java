package co.id.mii.serverapp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.id.mii.serverapp.models.Employee;
import co.id.mii.serverapp.models.User;
import co.id.mii.serverapp.models.dto.request.EmployeeRequest;
import co.id.mii.serverapp.repositories.EmployeeRepository;
import co.id.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServices {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private UserServices userService;

    public List<Employee> getall() {
        return employeeRepository.findAll();
    }

    public Employee getById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Employee is not found!!!"));
    }

    // public Employee create(EmployeeRequest employeeRequest) {
    //     if (employeeRequest.getName().isEmpty()) {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Name Empty!!!");
    //     }

    //     if (employeeRepository.existsByNameIgnoreCase(employeeRequest.getName())) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee Name already exists!!!");
    //     }

    //     if (employeeRequest.getEmail().isEmpty()) {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Email Empty!!!");
    //     }

    //     if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee Email already exists!!!");
    //     }

    //     if (employeeRequest.getPhone() == null) {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Phone is Empty!!!");
    //     }

    //     if (employeeRepository.existsByPhone(employeeRequest.getPhone())) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee Phone already exists!!!");
    //     }

    //     User findUserId = userService.getById(employeeRequest.getUserid());

    //     if (employeeRepository.existsByUser(findUserId)) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee is already associated with a User");
    //     }

    //     Employee employee = new Employee();
    //     employee.setName(employeeRequest.getName());
    //     employee.setEmail(employeeRequest.getEmail());
    //     employee.setPhone(employeeRequest.getPhone());
    //     employee.setUser(findUserId);
    //     findUserId.setEmployee(employee);
    //     userRepository.save(findUserId);

    //     return (employee);
    // }
    public Employee create(EmployeeRequest employeeRequest){
    

        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        User user = userService.getById(employeeRequest.getUserid());
        employee.setUser(user);
        user.setEmployee(employee);
        userRepository.save(user);
        return employee;

    }
    public Employee update(EmployeeRequest employeeRequest,Integer id){
        if(employeeRequest.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee Name Empty!!!");
        }

        if(employeeRepository.existsByNameIgnoreCase(employeeRequest.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Name already exists!!!");
        }

        if(employeeRequest.getEmail().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee Email Empty!!!");
        }

        if (employeeRepository.existsByEmail(employeeRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Email already exists!!!");
        }

        if(employeeRequest.getPhone() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee Phone is Empty!!!");
        }

        if (employeeRepository.existsByPhone(employeeRequest.getPhone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee Phone already exists!!!");
        }

        User findUserId = userService.getById(employeeRequest.getUserid());

        if (employeeRepository.existsByUser(findUserId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee is already associated with a User");
        }

        Employee employee = getById(id);
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setUser(findUserId);

        return employeeRepository.save(employee);
    }

    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

}
