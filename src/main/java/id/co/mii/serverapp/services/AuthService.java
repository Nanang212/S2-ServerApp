package id.co.mii.serverapp.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    
    private ModelMapper modelMapper;

    private EmployeeRepository employeeRepository;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private AppUserDetailService appUserDetailService;

    private UserService userService;

    private EmailService emailService;

    public Employee registration(RegistrationRequest registrationRequest){
        Employee employee = modelMapper.map(registrationRequest, Employee.class );
        User user = modelMapper.map(registrationRequest, User.class );
        
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        List<Role> roles = Collections.singletonList(roleService.getById(2));
        user.setRoles(roles);

        employee.setUser(user);
        user.setEmployee(employee);

        
       emailService.sendHtmlMessage(emailService.createVerifycationEmail(employee.getEmail(), user.getToken()));

        return employeeRepository.save(employee);

    } 

    public LoginResponse login(LoginRequest loginRequest){
        //set login
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        //set principle
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(auth);

        //set response
        User user = userRepository.findByUsernameOrEmployeeEmail(loginRequest.getUsername(),loginRequest.getPassword()).get();

        UserDetails userDetails = appUserDetailService.loadUserByUsername(loginRequest.getUsername());

        List<String> authorities = userDetails.getAuthorities()
                        .stream()
                        .map(authority -> authority.getAuthority())
                        .collect(Collectors.toList());

        return new LoginResponse(user.getUsername(),user.getEmployee().getEmail(), authorities);
    }

   public RegistrationRequest setNullField(RegistrationRequest registrationRequest){
        if (registrationRequest.getUsername() == null || registrationRequest.getUsername().isEmpty()) {
            registrationRequest.setUsername(UUID.randomUUID().toString());
        }
        if (registrationRequest.getPassword() == null || registrationRequest.getPassword().isEmpty()) {
            registrationRequest.setPassword(UUID.randomUUID().toString());
        }
        if (registrationRequest.getName() == null || registrationRequest.getName().isEmpty()) {
            registrationRequest.setName(UUID.randomUUID().toString());
        }
        if (registrationRequest.getEmail() == null || registrationRequest.getEmail().isEmpty()) {
            registrationRequest.setEmail(UUID.randomUUID().toString());
        }
        if (registrationRequest.getPhone() == null || registrationRequest.getPhone().isEmpty()) {
            registrationRequest.setPhone("081");
        }

        return registrationRequest;
    }

    public String isVerifyUser(String token){
      User existsUser = userService.findByToken(token).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token gak cocok"));
        // if token expired then send email again and return page token is expired 
        if (isTokenExpired(existsUser.getTokenExpiredAt())) {
            User regenerateToken = regenerateVerificationToken(existsUser);
            emailService.sendHtmlMessage(emailService.createVerifycationEmail(existsUser.getEmployee().getEmail(), regenerateToken.getToken()));
            userRepository.save(regenerateToken);
            return "token-expired";
        }


      if (existsUser.getIsEnable()) {
        
        return "page-404";
      } 
      existsUser.setIsEnable(true);
      userRepository.save(existsUser);
      return "registration";
    }

    public Boolean isTokenExpired(LocalDateTime tokenExpiredAt){
        return tokenExpiredAt.isBefore(LocalDateTime.now());
    }

    public User regenerateVerificationToken(User user){
        String newToken = UUID.randomUUID().toString();
        user.setToken(newToken);
        user.setTokenCreatedAt(LocalDateTime.now());
        user.setTokenExpiredAt(user.getTokenCreatedAt().plusHours(1));
        return user;
    }



}
