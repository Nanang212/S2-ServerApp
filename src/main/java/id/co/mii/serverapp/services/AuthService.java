package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.AppUserDetail;
import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.LoginRequest;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.responses.LoginResponse;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final AppUserDetailService appUserDetailService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Validator validator;


    public Employee registration(RegistrationRequest request) {
        Set<ConstraintViolation<RegistrationRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        Employee employee = modelMapper.map(request, Employee.class);
        User user = modelMapper.map(request, User.class);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository
            .findByNameIgnoreCase("ADMIN")
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));

        user.setRoles(Collections.singleton(role));

        employee.setUser(user);
        user.setEmployee(employee);

        return employeeRepository.save(employee);
    }

    public LoginResponse login(LoginRequest request) {
        Set<ConstraintViolation<LoginRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUserDetail appUserDetail = (AppUserDetail) authentication.getPrincipal();

        Set<String> authorities = appUserDetail
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());


        AppUserDetail userDetail = (AppUserDetail) appUserDetailService.loadUserByUsername(request.getUsername());
        User user = userDetail.getUser();

        LoginResponse response = new LoginResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmployee().getEmail());
        response.setAuthorities(authorities);

        return response;
    }
}
