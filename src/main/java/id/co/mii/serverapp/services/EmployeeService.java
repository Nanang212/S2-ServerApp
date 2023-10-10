package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;
import id.co.mii.serverapp.models.dto.requests.NewEmployeeRequest;
import id.co.mii.serverapp.models.dto.requests.VerifyRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.RoleRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Validator validator;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public Employee create(EmployeeRequest request) {
        Set<ConstraintViolation<EmployeeRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (employeeRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        if (employeeRepository.existsByUserId(request.getUserId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with id " + request.getUserId() + " already associate with an employee");
        }


        Employee employee = new Employee();

        return save(request, employee);
    }

    public Employee getById(Integer employeeId) {
        return employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public Employee getByToken(String token) {
        return employeeRepository
            .findByUserToken(token)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee update(Integer employeeId, EmployeeRequest request) {
        Set<ConstraintViolation<EmployeeRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        Employee employee = getById(employeeId);

        return save(request, employee);
    }

    private Employee save(EmployeeRequest request, Employee employee) {
        User user = userRepository
            .findById(request.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setUser(user);

        return employeeRepository.save(employee);
    }

    public Employee delete(Integer employeeId) {
        Employee employee = employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        employeeRepository.delete(employee);

        return employee;
    }

    @Transactional
    public Employee register(NewEmployeeRequest request) {
        Set<ConstraintViolation<NewEmployeeRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (employeeRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());

        Role role = roleRepository
            .findByNameIgnoreCase("USER")
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));

        User user = new User();
        user.setEmployee(employee);
        user.setToken(UUID.randomUUID().toString());
        user.setTokenExpiredAt(LocalDateTime.now().plusMinutes(30));
        user.setRoles(Collections.singleton(role));
        employee.setUser(user);

        employee = employeeRepository.save(employee);

        emailService.sendMessageWithHtml(
            new HashMap<String, Object>() {{
                put("token", user.getToken());
                put("name", request.getName());
            }},
            "emails/employee_verification",
            request.getEmail(),
            "Verify and Update Your Data",
            null
        );

        return employee;
    }

    @Transactional
    public Employee verify(VerifyRequest request) {
        Set<ConstraintViolation<VerifyRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (employeeRepository.existsByPhoneIgnoreCase(request.getPhone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone already exists");
        }

        if (userRepository.existsByUsernameIgnoreCase(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        Employee employee = getByToken(request.getToken());
        employee.getUser().setIsEnabled(true);
        employee.getUser().setToken(null);
        employee.getUser().setTokenExpiredAt(null);
        employee.setPhone(request.getPhone());

        employee.getUser().setUsername(request.getUsername());
        employee.getUser().setPassword(passwordEncoder.encode(request.getPassword()));

        return employeeRepository.save(employee);
    }
}
