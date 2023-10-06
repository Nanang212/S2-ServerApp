package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private AuthService authService;

    @GetMapping("/registration")
    public String createView(RegistrationRequest registrationRequest) {
        return "registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute RegistrationRequest registrationRequest) {
        authService.registration(registrationRequest);
        return "redirect:/registration";
    }
}
