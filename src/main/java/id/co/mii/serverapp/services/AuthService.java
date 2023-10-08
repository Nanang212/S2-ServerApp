package id.co.mii.serverapp.services;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.LoginRequest;
import id.co.mii.serverapp.models.dto.request.RegistrationRequest;
import id.co.mii.serverapp.models.dto.response.LoginResponse;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

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
    private EmailService emailService;

    public Employee registration(RegistrationRequest registrationRequest) {
        Employee employee = modelMapper.map(registrationRequest, Employee.class);
        User user = modelMapper.map(registrationRequest, User.class);

        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        List<Role> roles = Collections.singletonList(roleService.getById(1));
        user.setRoles(roles);
        employee.setUser(user);
        user.setEmployee(employee);

        return employeeRepository.save(employee);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        // set login
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());

        // set praciple
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // set response
        User user = userRepository.findByUsernameOrEmployeeEmail(loginRequest.getUsername(),
                loginRequest.getUsername()).get();
        UserDetails userDetails = appUserDetailService.loadUserByUsername(loginRequest.getUsername());

        List<String> authorities = userDetails.getAuthorities().stream().map(authority -> authority.getAuthority())
                .collect(Collectors.toList());
        return new LoginResponse(user.getUsername(), user.getEmployee().getEmail(), authorities);
    }

    public Employee signup(RegistrationRequest registrationRequest) {
        Employee employee = new Employee();
        employee.setName(registrationRequest.getName());
        employee.setEmail(registrationRequest.getEmail());
        employee.setUser(new User());

        User user = new User();
        user.setToken(UUID.randomUUID().toString());
        employeeRepository.save(employee);
        emailService.sendHtmlUser(user);
        return employee;
    }
}
