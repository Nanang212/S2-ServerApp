package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.dto.requests.LoginRequest;
import id.co.mii.serverapp.models.dto.responses.LoginResponse;
import id.co.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

//    @PostMapping("/registration")
//    public Employee registration(@RequestBody RegistrationRequest request) {
//        return authService.registration(request);
//    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/login-form")
    public ModelAndView login() {
        return new ModelAndView("views/login_form");
    }
}
