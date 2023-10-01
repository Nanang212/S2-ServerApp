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

import co.id.mii.serverapp.models.User;
import co.id.mii.serverapp.models.dto.request.UserRequest;
import co.id.mii.serverapp.services.EmployeeServices;
import co.id.mii.serverapp.services.UserServices;
import lombok.AllArgsConstructor;


@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserServices userServices;
    private EmployeeServices employeeServices;

    @GetMapping
    public List<User> findall(){
        return userServices.getAll();
    }
    @GetMapping("/{Id}")
    public User findById(@PathVariable Integer Id){
        return userServices.getById(Id);
    }

    @PostMapping
    public User create(@RequestBody UserRequest userRequest){
        return userServices.create(userRequest);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody UserRequest userRequest, @PathVariable Integer id){
        return userServices.update(userRequest,id);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Integer id){
        return userServices.delete(id);
    }
}
