package co.id.ms.mii.serverapp.controllers;

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

    @PostMapping
    public User create(@RequestBody User region){
        return userService.create(region);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Integer id){
        return userService.update(user,id);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Integer id){
        return userService.delete(id);
    }
}
