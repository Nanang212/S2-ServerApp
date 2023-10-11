package id.co.mii.serverapp.controllers;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.LoginRequest;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.responses.LoginResponse;
import id.co.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/registration")
    public Employee registration(@RequestBody RegistrationRequest registrationRequest) {
        return authService.registration(registrationRequest);
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/registration-validate")
    public Map<String, Object> validation(
            @RequestParam(name = "name") String name, @RequestParam(name = "email") String email) {
        return authService.validation(name, email);
    }

}
