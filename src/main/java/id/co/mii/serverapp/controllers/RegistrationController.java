package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.services.AuthService;
import id.co.mii.serverapp.services.EmployeeService;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class RegistrationController {
  private EmployeeService employeeService;
  private UserService userService;

  @GetMapping("/registration")
  public String createView(RegistrationRequest registrationRequest, @RequestParam(name = "token", required = false) String token, Model model) throws IOException {
    try {
      User user = userService.findByToken(token);
      model.addAttribute("id", user.getId());
      return "registration";
    } catch (ResponseStatusException exception) {
      return "redirect:http://localhost:8090/error?code=" + exception.getRawStatusCode() + "&message=" + exception.getStatus().getReasonPhrase();
    }
  }

  @PostMapping("/registration/{id}")
  public String create(@ModelAttribute RegistrationRequest registrationRequest, @PathVariable Integer id) {
    employeeService.update(id, registrationRequest);
    return "redirect:http://localhost:8090/auth/login?success=true&message=Registration Success";
  }
}
