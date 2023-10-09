package id.co.mii.serverapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class VerificationController {

    private UserService userService;

    @GetMapping("/verify-account")
    public String verifyView(@RequestParam(name = "token") String token){
        User user = userService.findByToken(token);
        if(user.getIsEnabled()){
            return "not-found";
        }
        return "verify-account";
    }
}
