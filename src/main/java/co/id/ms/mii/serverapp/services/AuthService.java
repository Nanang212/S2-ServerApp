package co.id.ms.mii.serverapp.services;


import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.models.Role;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public Employee registration(UserRequest userRequest) {
        Employee employee = modelMapper.map(userRequest, Employee.class);
        User user = modelMapper.map(userRequest, User.class);

        //  set password
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setIsEnabled(true);
        // set default role
        List<Role> roles = Collections.singletonList(roleService.getById(2));
        // List<Role> roles = new ArrayList<>();
        // roles.add(roleService.getById(2));
        user.setRoles(roles);

        employee.setUser(user);
        user.setEmployee(employee);

        return employeeRepository.save(employee);
    }
}
