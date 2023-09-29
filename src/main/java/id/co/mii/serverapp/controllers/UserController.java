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

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserEmployeeRequest;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PostMapping
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Integer id){
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Integer id){
        return userService.delete(id);
    }

    @PostMapping("/create-user-employee")
    public User createUserEmployee(@RequestBody UserEmployeeRequest request){
        return userService.createUserEmployee(request);
    }
}
