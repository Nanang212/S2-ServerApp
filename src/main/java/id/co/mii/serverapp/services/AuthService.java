package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.LoginRequest;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.responses.LoginResponse;
import id.co.mii.serverapp.repositories.EmployeRepo;
import id.co.mii.serverapp.repositories.UserRepo;
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

import java.util.Collections;
import java.util.List;
import java.util.Set;
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

    public Employee registration(RegistrationRequest registrationRequest) {
        if (userRepo.existsByUsername(registrationRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used !!!");
        }
        if (employeRepo.existsByEmail(registrationRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used !!!");
        }
        if (employeRepo.existsByPhone(registrationRequest.getPhone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used !!!");
        }
        Employee employee = modelMapper.map(registrationRequest, Employee.class);
        User user = modelMapper.map(registrationRequest, User.class);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        List<Role> roles = Collections.singletonList(roleService.getById(2));
        user.setEmployee(employee);

        employee.setUser(user);
        user.setRoles(roles);

        employeRepo.save(employee);
        return employee;
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
}
