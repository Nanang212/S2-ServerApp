package id.co.mii.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.request.RegistrationRequest;
import id.co.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/registration")
    public Employee registration(@RequestBody RegistrationRequest registrationRequest) {
        return authService.registration(registrationRequest);
    }

}
