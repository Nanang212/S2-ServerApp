package id.co.mii.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.LoginRequest;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.respones.LoginResponse;
import id.co.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping
public class AuthController {
    
    private AuthService authService;

    @PostMapping("/registration")
    public Employee registration(@RequestBody RegistrationRequest registrationRequest ){
        return authService.registration(registrationRequest);
    }

    //     @PostMapping("/registration")
    // public String registration(@RequestBody RegistrationRequest registrationRequest ){
    //     return "jalan" + registrationRequest.getUsername();
    // }

    // @PostMapping("/login")
    // public LoginResponse login(@RequestBody LoginRequest loginRequest){
    //     return authService.login(loginRequest);
    // }

        @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return "jalan" + loginRequest.getUsername();
    }
}
