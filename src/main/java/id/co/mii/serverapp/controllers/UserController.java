package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.UserDTO;
import id.co.mii.serverapp.models.request.CreateUserRequest;
import id.co.mii.serverapp.models.request.UpdateUserRequest;
import id.co.mii.serverapp.services.UserServices;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private UserServices userServices;

    @GetMapping
    public List<User> getAll() {
        return userServices.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userServices.getById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userServices.update(id, user);
    }

    // add role
    @PutMapping("/add-role/{id}")
    public User addRole(@PathVariable Integer id, @RequestBody Role role) {
        return userServices.addRole(id, role);
    }
}
