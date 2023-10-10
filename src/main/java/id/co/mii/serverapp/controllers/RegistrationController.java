package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.RegistrationRequest;
import id.co.mii.serverapp.services.EmployeeService;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private EmployeeService employeeService;
    private UserService userService;

    @GetMapping("/page/registration")
    public String registrationView(RegistrationRequest registrationRequest,
            @RequestParam(name = "token", required = false) String token,
            Model model) {
        Optional<User> user = userService.findByToken(token);
        if (!user.isPresent()) {
            return "not-found";
        }
        model.addAttribute("id", user.get().getId());
        return "verif-page";
    }

    @PostMapping("/registration/{id}")
    public String registration(@ModelAttribute RegistrationRequest registrationRequest, @PathVariable Integer id) {
        employeeService.update(id, registrationRequest);
        return "success";
    }

}
