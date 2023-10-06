package co.id.mii.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.mii.serverapp.models.Employee;
import co.id.mii.serverapp.models.dto.request.RegistrationRequest;
import co.id.mii.serverapp.services.AuthServices;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class AuthController {
    private AuthServices authServices;

    @PostMapping("/registration")
    public Employee registration(@RequestBody RegistrationRequest registrationRequest){
        return authServices.registration(registrationRequest);
    }
}
