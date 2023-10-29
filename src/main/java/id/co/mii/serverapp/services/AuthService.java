package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import id.co.mii.serverapp.models.dto.requests.LoginRequest;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.responses.LoginResponse;
import id.co.mii.serverapp.models.dto.responses.RegistrationResponse;
import id.co.mii.serverapp.repositories.EmployeRepo;
import id.co.mii.serverapp.repositories.UserRepo;
import id.co.mii.serverapp.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {
  private UserRepo userRepo;
  private EmployeRepo employeRepo;
  private ModelMapper modelMapper;
  private RoleService roleService;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private AppUserDetailService userDetailService;
  private EmailService emailService;

  public RegistrationResponse registration(RegistrationRequest registrationRequest) {
    if (!StringUtils.isEmptyOrNull(registrationRequest.getUsername())) {
      if (userRepo.existsByUsername(registrationRequest.getUsername())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used !!!");
      }
    }
    if (!StringUtils.isEmptyOrNull(registrationRequest.getEmail())) {
      if (employeRepo.existsByEmail(registrationRequest.getEmail())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
      }
    }
    if (!StringUtils.isEmptyOrNull(registrationRequest.getPhone())) {
      if (employeRepo.existsByPhone(registrationRequest.getPhone())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
      }
    }
    Employee employee = modelMapper.map(registrationRequest, Employee.class);
    User user = modelMapper.map(registrationRequest, User.class);
//    List<Role> roles = Collections.singletonList(roleService.getById(2));
    if (!StringUtils.isEmptyOrNull(registrationRequest.getPassword())) {
      user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
    }
    user.setToken(UUID.randomUUID().toString());
//    user.setIsEnable(true);
    user.setEmployee(employee);
    if (registrationRequest.getRoleIds() != null) {
      user.setRoles(mapToRoles(registrationRequest.getRoleIds()));
    } else {
      List<Role> roles = Collections.singletonList(roleService.getById(2));
      user.setRoles(roles);
    }
    employee.setUser(user);

    Employee savedEmployee = employeRepo.save(employee);
    RegistrationResponse registrationResponse = modelMapper.map(savedEmployee, RegistrationResponse.class);
    registrationResponse.setUsername(savedEmployee.getUser().getUsername());

    EmailRequest emailRequest = new EmailRequest();
    Map<String, Object> properties = new HashMap<>();
    properties.put("token", user.getToken());
    properties.put("employeeName", employee.getName());
    emailRequest.setTo(registrationRequest.getEmail());
    emailRequest.setSubject("Verification email");
    emailRequest.setBody("emails/test.html");
    emailRequest.setProperties(properties);
    emailService.sendHtmlMessage(emailRequest);

    return registrationResponse;
  }

  public LoginResponse login(LoginRequest loginRequest) {
    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
    Authentication authentication = authenticationManager.authenticate(authReq);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    User user = userRepo.findByUsernameOrEmployeeEmail(loginRequest.getUsername(), loginRequest.getUsername()).get();
    UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getUsername());

    List<String> authorities = userDetails.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return new LoginResponse(user.getUsername(), user.getEmployee().getEmail(), authorities);
  }

  private List<Role> mapToRoles(List<Integer> roleIds) {
    return roleIds
            .stream()
            .map(roleId -> roleService.getById(roleId))
            .collect(Collectors.toList());
  }
}
