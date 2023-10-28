package id.co.mii.serverapp.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.services.AuthService;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private EmployeeService employeeService;
    private AuthService authService;

    @GetMapping("/register")
    public String createView(RegistrationRequest registrationRequest,
            @RequestParam(name = "uuid", required = false) String uuid, Model model) {

        Optional<Employee> employee = employeeService.findByUuid(uuid);

        if (!employee.isPresent()) {
            return "Form-Failed";
        }
        model.addAttribute("id", employee.get().getUser().getId());
        return "Form";
    }

    @PostMapping("/register/{id}")
    public String create(@ModelAttribute RegistrationRequest registrationRequest, @PathVariable Integer id) {
        employeeService.update(id, registrationRequest);
        return "Form-Succes";
    }

    @GetMapping("/adduser")
    public String registerView() {
        return "register";
    }

    @PostMapping("/adduser")
    public String registration(
            @ModelAttribute RegistrationRequest registrationRequest) {
        authService.registration(registrationRequest);
        return "EmailRegistered";
    }

}
