package id.co.mii.serverapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.services.EmployeeService;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private UserService userService;
    private EmployeeService employeeService;

    @GetMapping("page/registration")
    public String registrationView(RegistrationRequest registrationRequest,
            @RequestParam(name = "token", required = false) String token,
            Model model) {
        if (userService.verify(token)) {
            User user = userService.findByToken(token);
            model.addAttribute("id", user.getId());
            return "registration";
        }
        return "not-found";
    }

    @PostMapping("/registration/{id}")
    public String registration(@ModelAttribute RegistrationRequest registrationRequest, @PathVariable Integer id) {
        employeeService.update(id, registrationRequest);
        return "success";
    }
    
}
