package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.dto.request.UserRequest;
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
    public User create(@RequestBody UserRequest userRequest){
        return userService.create(userRequest);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody UserRequest userRequest, @PathVariable Integer id){
        return userService.update(userRequest,id);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Integer id){
        return userService.delete(id);
    }
}
