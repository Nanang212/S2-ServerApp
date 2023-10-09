package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.EmailRequest;
import id.co.mii.serverapp.models.dto.request.LoginRequest;
import id.co.mii.serverapp.models.dto.request.RegistrationRequest;
import id.co.mii.serverapp.models.dto.response.LoginResponse;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import lombok.AllArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
// import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
@AllArgsConstructor
public class AuthService {

  private EmployeeRepository employeeRepository;
  // private ModelMapper modelMapper;
  private RoleService roleService;
  // private PasswordEncoder passwordEncoder;
  private AuthenticationManager authManager;
  private UserRepository userRepository;
  private AppUserDetailService appUserDetailService;
  private EmailRequest emailRequest;
  private JavaMailSender javaMailSender;
  private SpringTemplateEngine springTemplateEngine;

  public Employee registration(RegistrationRequest registrationRequest) {
    Employee employee = new Employee();
    User user = new User();
    UUID uuid = UUID.randomUUID();

    employee.setEmail(registrationRequest.getEmail());
    employee.setName(registrationRequest.getName());
    employee.setUuid(uuid.toString());

    List<Role> roles = Collections.singletonList(roleService.getById(2));
    user.setRoles(roles);

    employee.setUser(user);
    user.setEmployee(employee);

    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_NO,
          StandardCharsets.UTF_8.name());
      Context context = new Context();
      context.setVariable("message", emailRequest);
      String htmlContent = springTemplateEngine.process("konfirmasiemail.html", context);
      helper.setTo(registrationRequest.getEmail());
      helper.setSubject("Email Confirmation");
      helper.setText(htmlContent, true);
      javaMailSender.send(message);

    } catch (Exception e) {
      System.out.println("Error : " + e.getMessage());
    }

    return employeeRepository.save(employee);
  }

  public LoginResponse login(LoginRequest loginRequest) {
    // set login
    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
        loginRequest.getPassword());

    // set princile untuk menyimpan cookies/session
    Authentication auth = authManager.authenticate(authReq);
    SecurityContextHolder.getContext().setAuthentication(auth);

    // set response
    User user = userRepository.findByUsernameOrEmployeeEmail(loginRequest.getUsername(), loginRequest.getUsername())
        .get();
    UserDetails userDetails = appUserDetailService.loadUserByUsername(loginRequest.getUsername());
    List<String> authorities = userDetails.getAuthorities().stream().map(authority -> authority.getAuthority())
        .collect(Collectors.toList());

    return new LoginResponse(user.getUsername(), user.getEmployee().getEmail(), authorities);

  }

  // public Employee signUp(SignUpRequest signUpRequest){

  // Employee employee = modelMapper.map(signUpRequest, Employee.class);
  // User user = new User();
  // user.setPassword(passwordEncoder.encode(user.getPassword()));
  // List<Role> roles = Collections.singletonList(roleService.getById(2));
  // user.setRoles(roles);
  // user.setPassword(passwordEncoder.encode("nopassword"));
  // employee.setUser(user);
  // user.setEmployee(employee);
  // return employeeRepository.save(employee);

  // // User user = new User();
  // // Employee employee = modelMapper.map(signUpRequest, Employee.class);
  // // List<Role> roles = Collections.singletonList(roleService.getById(2));
  // // user.setRoles(roles);
  // // employee.setUser(user);
  // // user.setEmployee(employee);
  // // return employeeRepository.save(employee);
  // }
}