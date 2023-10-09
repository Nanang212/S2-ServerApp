package co.id.ms.mii.serverapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/verify")
public class VerifiyController {

    @GetMapping
    public ModelAndView verify(@PathParam("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("PageForm");
        return modelAndView;
    }
}
