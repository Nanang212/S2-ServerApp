package id.co.mii.serverapp.services;

import java.util.Collections;
import java.util.List;
// import java.util.Set;

// import org.hibernate.mapping.List;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.request.RegistrationRequest;
import id.co.mii.serverapp.repositories.EmployeeRepositories;
// import id.co.mii.serverapp.utils.PasswordEncoderUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private EmployeeRepositories employeeRepositories;
    private ModelMapper modelMapper;
    private RoleServices roleServices;
    private PasswordEncoder passwordEncoder;

    public Employee registration(RegistrationRequest registrationRequest) {
        Employee employee = modelMapper.map(registrationRequest, Employee.class);
        User user = modelMapper.map(registrationRequest, User.class);

        // set password
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // set default role
        List<Role> roles = Collections.singletonList(roleServices.getById(2));
        user.setRoles(roles);

        employee.setUser(user);
        user.setEmployee(employee);

        return employeeRepositories.save(employee);
    }
}
