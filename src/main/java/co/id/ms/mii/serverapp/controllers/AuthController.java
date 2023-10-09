package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.dto.request.EmployeeRequest;
import co.id.ms.mii.serverapp.dto.request.LoginRequest;
import co.id.ms.mii.serverapp.dto.request.RegistrationRequest;
import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.dto.response.LoginResponse;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RestController
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/registration")
    public Employee registration(
            @RequestBody UserRequest userRequest
    ) {
        return authService.registration(userRequest);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public Employee signup(@RequestBody RegistrationRequest registrationRequest){
        return authService.signup(registrationRequest);
    }

    @PutMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> formData) {
            // Extract form fields from 'formData' map
            String username = formData.get("username");
            String phone = formData.get("phone");
            String password = formData.get("password");
            String token = formData.get("token");

            authService.register(username,password,phone,token);

            // You can return a success message or other data as needed
            return ResponseEntity.ok("Registration successful");
    }

}
