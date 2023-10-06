package co.id.ms.mii.serverapp.services;


import co.id.ms.mii.serverapp.dto.request.LoginRequest;
import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.dto.response.LoginResponse;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.models.Role;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.repositories.EmployeeRepository;
import co.id.ms.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private AppUserDetailService appUserDetailService;

    public Employee registration(UserRequest userRequest) {
        Employee employee = modelMapper.map(userRequest, Employee.class);
        User user = modelMapper.map(userRequest, User.class);

        //  set password
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setIsEnabled(true);
        // set default role
        List<Role> roles = Collections.singletonList(roleService.getById(1));
        // List<Role> roles = new ArrayList<>();
        // roles.add(roleService.getById(2));
        user.setRoles(roles);

        employee.setUser(user);
        user.setEmployee(employee);

        return employeeRepository.save(employee);
    }

    public LoginResponse login(LoginRequest loginRequest){
        // set login using authenticator
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        // set principle untuk menyimpan kedalam cookies dan setting session token
        Authentication authentication = authenticationManager.authenticate(authRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //setting response header
        User user = userRepository.findByUsernameOrAndEmployee_Email(
                loginRequest.getUsername(),
                loginRequest.getUsername()
        ).get();

        UserDetails userDetails = appUserDetailService.loadUserByUsername(
                loginRequest.getUsername()
        );

        List<String> authorities = userDetails.getAuthorities()
                .stream().map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        return new LoginResponse(
                user.getUsername(),
                user.getEmployee().getEmail(),
                authorities
        );
    }
}
