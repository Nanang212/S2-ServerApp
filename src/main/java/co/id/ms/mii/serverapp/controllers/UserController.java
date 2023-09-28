package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.getall();
    }

    @GetMapping("/{Id}")
    public User findById(@PathVariable Integer Id){
        return userService.getById(Id);
    }

    @PostMapping
    public User create(@RequestBody User region){
        return userService.create(region);
    }
}
