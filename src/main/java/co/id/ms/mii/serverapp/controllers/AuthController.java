package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.dto.request.*;
import co.id.ms.mii.serverapp.dto.response.LoginResponse;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.services.AuthService;
import co.id.ms.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.Map;

@Controller
@RestController
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    private EmployeeService employeeService;

    @PostMapping("/registration")
    public Employee registration(
            @RequestBody UserRequest userRequest
    ) {
        return authService.registration(userRequest);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public Employee signup(@RequestBody RegistrationRequest registrationRequest){
        return authService.signup(registrationRequest);
    }

    @GetMapping("/verify")
    public ModelAndView verify(@PathParam("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("PageForm");
//        return modelAndView;
        try {
            Employee findemployee = employeeService.getByUserToken(token);

            if(findemployee.getUser().getIsEnabled()){
                modelAndView.setViewName("PageNotFound");
            } else {
                modelAndView.setViewName("PageForm");
                modelAndView.addObject("token",token);
            }
        }catch (Exception e){
            System.out.println("gak onok");
        }
        return modelAndView;
    }

    @PutMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignupRequest request) {
            // Extract form fields from 'formData' map

            authService.saveregister(request);

            // You can return a success message or other data as needed
            return ResponseEntity.ok("Registration successful");
    }

}
