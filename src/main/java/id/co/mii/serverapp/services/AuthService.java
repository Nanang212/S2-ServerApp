package id.co.mii.serverapp.services;

import java.util.*;
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
import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import id.co.mii.serverapp.models.dto.requests.LoginRequest;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.responses.LoginResponse;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;
    private AuthenticationManager authManager;
    private UserRepository userRepository;
    private UserService userService;
    private AppUserDetailService appUserDetailService;
    private EmailService emailService;
    private PasswordEncoder passwordEncoder;

    public Employee registration(RegistrationRequest registrationRequest) {

        Employee employee = modelMapper.map(registrationRequest, Employee.class);
        User user = modelMapper.map(registrationRequest, User.class);
        List<Role> roles;
        // set default role
        if (Objects.equals(registrationRequest.getName(), "admin")) {
            roles = Collections.singletonList(roleService.getById(1));
        } else {
            roles = Collections.singletonList(roleService.getById(2));
        }
        // List<Role> roles = new ArrayList<>();
        // roles.add(roleService.getById(2));

        user.setRoles(roles);
        user.setToken(UUID.randomUUID().toString());

        employee.setUser(user);
        user.setEmployee(employee);
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(registrationRequest.getEmail());
        emailRequest.setFrom("arifhanif2000@gmail.com");
        emailRequest.setSubject("Verification Account");
        emailRequest.setTemplate("welcome-email.html");
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", registrationRequest.getName());
        properties.put("token", user.getToken());

        emailRequest.setProperties(properties);

        emailService.sendHtml(emailRequest);

        return employeeRepository.save(employee);
    }

    public LoginResponse login(LoginRequest loginRequest) {

        // set login
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        // set principle
        Authentication auth = authManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // set response
        User user = userRepository
                .findByUsernameOrEmployeeEmail(
                        loginRequest.getUsername(),
                        loginRequest.getUsername())
                .get();

        UserDetails userDetails = appUserDetailService.loadUserByUsername(
                loginRequest.getUsername());

        List<String> authorities = userDetails
                .getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        return new LoginResponse(
                user.getUsername(),
                user.getEmployee().getEmail(),
                authorities);
    }

    public User verification(RegistrationRequest registrationRequest, String token) {

        User user = userService.findByToken(token);

        user.setIsEnabled(true);
        user.setToken(null);
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.getEmployee().setPhone(registrationRequest.getPhone());

        return userRepository.save(user);
    }

    public Map<String, Object> validation(String name, String email) {

        Map<String, Object> errors = new HashMap<>();

        if (name.isEmpty()) {
            errors.put("name", "Name cant be empty!");
        }

        if (email.isEmpty()) {
            errors.put("email", "Email cant be empty!");
        }

        if (employeeRepository.findByName(name) != null) {
            errors.put("name", "Name Already Exists!");
        }

        if (employeeRepository.findByEmail(email) != null) {
            errors.put("email", "Email Already Exists!");
        }

        return errors;

    }
}
