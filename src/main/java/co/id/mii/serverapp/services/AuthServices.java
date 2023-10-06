package co.id.mii.serverapp.services;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.id.mii.serverapp.models.Employee;
import co.id.mii.serverapp.models.Role;
import co.id.mii.serverapp.models.User;
import co.id.mii.serverapp.models.dto.request.RegistrationRequest;
import co.id.mii.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServices {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RoleServices roleServices;
    private PasswordEncoder passwordEncoder;

    public Employee registration(RegistrationRequest registrationRequest) {
        Employee employee = modelMapper.map(registrationRequest, Employee.class);
        User user = modelMapper.map(registrationRequest, User.class);

        // set password
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // set default role
        List<Role> roles = Collections.singletonList(roleServices.getById(1));
        user.setRoles(roles);

        employee.setUser(user);
        user.setEmployee(employee);

        return employeeRepository.save(employee);
    }
}
