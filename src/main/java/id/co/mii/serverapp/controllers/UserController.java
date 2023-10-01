package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
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

    // with dto model mapper
    @PostMapping("/create")
    public User create(
        @RequestBody UserRequest userRequest
    ) {
        return userService.create(userRequest);
    }

    // update with dto
    @PutMapping("/{id}")
    public User update(@PathVariable Integer id,  @RequestBody UserRequest userRequest){
         return userService.update(id, userRequest);
    }

    // search by name
    
    // JPQL
    @GetMapping("/search")
    public List<User> search(
        @RequestParam(name = "name") String name
    ){
        return userService.search(name);
    }

    // delete
    @DeleteMapping("/{id}")
    public User delete(@PathVariable Integer id){
        return userService.delete(id);
    }

}
