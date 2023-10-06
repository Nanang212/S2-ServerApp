package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(
        value = "/user",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody UserRequest request) {
        return userService.create(request);
    }

    @GetMapping(
        value = "/user/{userId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User getById(@PathVariable Integer userId) {
        return userService.getById(userId);
    }

    @GetMapping(
        value = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping(
        value = "/user/{userId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User create(@PathVariable Integer userId, @RequestBody UserRequest request) {
        return userService.update(userId, request);
    }

    @DeleteMapping(
        value = "/user/{userId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User delete(@PathVariable Integer userId) {
        return userService.delete(userId);
    }

    @PatchMapping(
        value = "/user/{userId}/role",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User addRole(@PathVariable Integer userId, @RequestBody Set<Integer> roleIds) {
        return userService.addRole(userId, roleIds);
    }
}
