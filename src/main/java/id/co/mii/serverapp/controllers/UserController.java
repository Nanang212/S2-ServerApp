package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/find")
    public User getByUsername(@RequestParam(required = false) String username) {
        return userService.findByUsername(username);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
