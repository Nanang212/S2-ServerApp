package co.id.mii.serverapp.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.id.mii.serverapp.models.Employee;
import co.id.mii.serverapp.models.Role;
import co.id.mii.serverapp.models.User;
import co.id.mii.serverapp.models.dto.request.LoginRequest;
import co.id.mii.serverapp.models.dto.request.RegistrationRequest;
import co.id.mii.serverapp.models.responses.LoginRespons;
import co.id.mii.serverapp.repositories.EmployeeRepository;
import co.id.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServices {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RoleServices roleServices;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authManager;
  private UserRepository userRepository;
  private AppUserDetailsServices appUserDetailsServices;

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
    public LoginRespons login(LoginRequest loginRequest) {
    // set login
    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
      loginRequest.getUsername(),
      loginRequest.getPassword()
    );

    // set principle
    Authentication auth = authManager.authenticate(authReq);
    SecurityContextHolder.getContext().setAuthentication(auth);

    // set response
    User user = userRepository
      .findByUsernameOrEmployeeEmail(
        loginRequest.getUsername(),
        loginRequest.getUsername()
      )
      .get();

    UserDetails userDetails = appUserDetailsServices.loadUserByUsername(loginRequest.getUsername());

    List<String> authorities = userDetails
      .getAuthorities()
      .stream()
      .map(authority -> authority.getAuthority())
      .collect(Collectors.toList());

    return new LoginRespons(
      user.getUsername(),
      user.getEmployee().getEmail(),
      authorities
    );
  }
}
