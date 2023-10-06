package co.id.mii.serverapp.controllers;

import java.util.List;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.mii.serverapp.models.Role;
import co.id.mii.serverapp.models.User;
import co.id.mii.serverapp.models.dto.request.UserRequest;
import co.id.mii.serverapp.services.EmployeeServices;
import co.id.mii.serverapp.services.UserServices;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserServices userServices;

    @GetMapping
    public List<User> findall() {
        return userServices.getAll();
    }

    @GetMapping("/{Id}")
    public User findById(@PathVariable Integer Id) {
        return userServices.getById(Id);
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
