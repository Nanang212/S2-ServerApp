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
    public List<UserDTO> getAll(){
        return userServices.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        return userServices.getById(id);
    }

    @PostMapping
    public UserDTO create(@RequestBody CreateUserRequest request){
        return userServices.createUser(request);
    }

    @PutMapping("/{id}")
 public UserDTO update(@PathVariable Integer id, @RequestBody UpdateUserRequest request){
    return userServices.updateUser(id, request);
}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        userServices.delete(id);
    }
}
