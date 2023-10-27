package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.repositories.EmployeeRepository;
import co.id.ms.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserProfileService {
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;

    public Employee findByName(String name) {
        return employeeRepository.findByUserUsernameIgnoreCase(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee username not found")
        );
    }

    public Employee updateEmployeeProfile(String name, UserRequest userRequest){
        Employee findEmployee = employeeRepository.findByUserUsernameIgnoreCase(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee username not found")
        );


        User finduser = userRepository.findByUsernameIgnoreCase(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User Employee name not Found!!!")
        );

        findEmployee.setName(userRequest.getName());
        findEmployee.setEmail(userRequest.getEmail());
        findEmployee.setPhone(userRequest.getPhone());

        finduser.setUsername(userRequest.getUsername());

        findEmployee.setUser(finduser);
        finduser.setEmployee(findEmployee);

        return employeeRepository.save(findEmployee);
    }
}
