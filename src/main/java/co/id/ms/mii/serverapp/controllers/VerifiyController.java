package co.id.ms.mii.serverapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/verify")
public class VerifiyController {

    @GetMapping
    public String verify(@PathParam("token") String token) {

        return "berhasil";
    }
}
