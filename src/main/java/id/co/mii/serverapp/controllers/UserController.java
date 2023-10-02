package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        Optional<User> user = userService.getById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @PostMapping
    public User create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody UserRequest user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public Optional<User> delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
