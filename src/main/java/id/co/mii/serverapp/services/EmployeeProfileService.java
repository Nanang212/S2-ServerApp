package id.co.mii.serverapp.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.ChangePasswordRequest;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeProfileService {
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    public Employee findByName(String name) {
        return employeeRepository.findByUserUsernameIgnoreCase(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee username not found"));
    }

    public Employee updateEmployeeProfile(String name, UserRequest userRequest) {
        Employee findEmployee = employeeRepository.findByUserUsernameIgnoreCase(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee username not found"));

        User finduser = userRepository.findByUsernameIgnoreCase(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Employee name not Found!!!"));

        findEmployee.setName(userRequest.getName());
        findEmployee.setEmail(userRequest.getEmail());
        findEmployee.setPhone(userRequest.getPhone());

        finduser.setUsername(userRequest.getUsername());

        findEmployee.setUser(finduser);
        finduser.setEmployee(findEmployee);

        return employeeRepository.save(findEmployee);
    }

    public Employee changePasswordProfile(String name, ChangePasswordRequest changePasswordRequest) {
        Employee currentEmpProfile = findByName(name);

        String newPassword = changePasswordRequest.getNewPassword();
        String oldPassword = changePasswordRequest.getOldPassword();

        if (!passwordEncoder.matches(oldPassword, currentEmpProfile.getUser().getPassword())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Bad Credential");
        }
        if (!changePasswordRequest.getNewPassword().equalsIgnoreCase(changePasswordRequest.getOldPassword())) {
            currentEmpProfile.getUser().setPassword(passwordEncoder.encode(newPassword));
            employeeRepository.save(currentEmpProfile);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BADUD REQUEST");
        }

        return currentEmpProfile;
    }
}
