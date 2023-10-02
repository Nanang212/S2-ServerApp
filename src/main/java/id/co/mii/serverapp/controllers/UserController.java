package id.co.mii.serverapp.controllers;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.UserRequest;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping
    public User insertDTO(@RequestBody UserRequest userRequest) {
        return userService.insertDTO(userRequest);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user){
        return userService.update(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public User delete(@PathVariable Integer id){
        return userService.delete(id);
    }
}
