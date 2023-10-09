package id.co.mii.serverapp.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.services.AuthService;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/verify-account")
@AllArgsConstructor
public class VerificationController {

    private UserService userService;
    private AuthService authService;

    @GetMapping
    public ModelAndView verifyForm(@RequestParam(name = "token") String token) {
        ModelAndView mv = new ModelAndView();

        User user = userService.findByToken(token);

        if (user == null) {
            mv.setViewName("404");
            return mv;
        }

        mv.setViewName("verify-account");
        mv.getModel().put("employee", user.getEmployee());
        return mv;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView verification(@RequestParam(name = "token") String token,
            @ModelAttribute RegistrationRequest registrationRequest) {

        ModelAndView mv = new ModelAndView();

        if (!token.isEmpty()) {

            User user = authService.verification(registrationRequest, token);

            mv.setViewName("verify-success");
            mv.getModel().put("name", user.getEmployee().getName());

            return mv;
        }

        mv.setViewName("404");
        return mv;
    }
}
