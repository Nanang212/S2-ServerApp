package id.co.mii.serverapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.request.RegistrationRequest;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegistrationController {

    EmployeeService employeeService;

    @GetMapping("/register")
    public String createView(RegistrationRequest registrationRequest,
            @RequestParam(name = "uuid", required = false) String uuid, Model model) {
        Employee employee = employeeService.findByUuid(uuid);
        if (employee.getUser().getIsEnabled()) {
            return "not-found";
        }
        model.addAttribute("id", employee.getUser().getId());
        return "form";
    }

    @PostMapping("/register/{id}")
    public String create(@ModelAttribute RegistrationRequest registrationRequest, @PathVariable Integer id) {
        employeeService.update(id, registrationRequest);
        return "success";
    }

    @GetMapping("/login")
    public String viewLogin(Model model) {
        return "login";
    }

}
