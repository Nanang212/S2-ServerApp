package co.id.ms.mii.serverapp.services;


import co.id.ms.mii.serverapp.dto.request.LoginRequest;
import co.id.ms.mii.serverapp.dto.request.RegistrationRequest;
import co.id.ms.mii.serverapp.dto.request.SignupRequest;
import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.dto.response.LoginResponse;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.models.Role;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.repositories.EmployeeRepository;
import co.id.ms.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
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
    private EmailService emailService;

    public Employee registration(UserRequest userRequest) {
        Employee employee = modelMapper.map(userRequest, Employee.class);
        User user = modelMapper.map(userRequest, User.class);

        //  set password
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setIsEnabled(true);
        // set default role
        List<Role> roles = Collections.singletonList(roleService.getById(1));
        user.setRoles(roles);
        // set isEnable to true
        user.setIsEnabled(true);
        // set token to null untuk menghilangkan token sek gak isok
        user.setToken(null);

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

    public Employee signup(RegistrationRequest registrationRequest){
        if(registrationRequest.getName().isEmpty()){
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name is empty!!");
        }
        if(registrationRequest.getEmail().isEmpty()){
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email is empty!!");
        }

        Employee employee = new Employee();
        employee.setName(registrationRequest.getName());
        employee.setEmail(registrationRequest.getEmail());

        

        User user = new User();
        user.setToken(UUID.randomUUID().toString());

        // jika di set true maka pada saat verifikasi akan terjadi page 404
//        user.setIsEnabled(true);

        user.setEmployee(employee);
        // user.setIsEnabled(true);
        employee.setUser(user);

        employeeRepository.save(employee);

        emailService.sendHtmlsignup(employee);

        return employee;
    }

    public void saveregister(SignupRequest request){
            Employee findemployee = employeeRepository.findByUserToken(request.getToken()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
            findemployee.setPhone(request.getPhone());
            findemployee.getUser().setUsername(request.getUsername());
            findemployee.getUser().setPassword(passwordEncoder.encode(request.getPassword()));

            List<Role> roles = new ArrayList<>();
            roles.add(roleService.getById(2));
            findemployee.getUser().setRoles(roles);
            findemployee.getUser().setToken(null);
            findemployee.getUser().setIsEnabled(true);

            employeeRepository.save(findemployee);
    }
}
