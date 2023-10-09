package id.co.mii.serverapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.co.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthUIController {

    private AuthService authService;
    
    @GetMapping("/registration")
    String registration(){
        return "registration";
    }

    @GetMapping("/verify")
    String verify(@RequestParam(name = "token", required = false)  String token){
        return authService.isVerifyUser(token);
    }
}
