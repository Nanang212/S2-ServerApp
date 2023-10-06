package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.models.Role;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.services.EmployeeService;
import co.id.ms.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private EmployeeService employeeService;

    @GetMapping
    public List<User> findAll() {
        return userService.getall();
    }

    @GetMapping("/{Id}")
    public User findById(@PathVariable Integer Id){
        return userService.getById(Id);
    }

//    @PostMapping
//    public User create(@RequestBody UserRequest userRequest){
//        return userService.create(userRequest);
//    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }

    // add role
    @PutMapping("/add-role/{id}")
    public User addRole(@PathVariable Integer id, @RequestBody Role role) {
        return userService.addRole(id, role);
    }

//    @DeleteMapping("/{id}")
//    public User delete(@PathVariable Integer id){
//        return userService.delete(id);
//    }
}
