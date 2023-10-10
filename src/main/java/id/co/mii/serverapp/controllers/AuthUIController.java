package id.co.mii.serverapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthUIController {

    private AuthService authService;
    
    @GetMapping("/verify")
    String verify(@RequestParam(name = "token", required = false)  String token, Model model){
        model.addAttribute("registerRequest", new RegistrationRequest());
        model.addAttribute("id", token);
        return authService.isVerifyUser(token);
    }

    @PostMapping("/register-user/{id}")
    String registerUser(@ModelAttribute RegistrationRequest registrationRequest ,Model model, @PathVariable String id){
        Employee registerUser = authService.registerUser(registrationRequest, id);
        
        model.addAttribute("registerRequest", registerUser);  
        return "success";
    }
}
