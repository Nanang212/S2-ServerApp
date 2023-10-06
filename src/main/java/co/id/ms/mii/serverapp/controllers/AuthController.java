package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
