package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.services.RoleService;
import lombok.AllArgsConstructor;

@RestController

@AllArgsConstructor
public class RoleController {
    
    private RoleService roleService;

    @GetMapping("/role")
    public List<Role> getAll(){
        return roleService.getAll();
    }

    @GetMapping("/role/{id}")
    public Role getById(@PathVariable Integer id){
        return roleService.getById(id);
    }

    @PostMapping("/role")
    public Role create(@RequestBody Role role){
        return roleService.create(role);
    }

    @PutMapping("/role/{id}") 
    public Role update(@RequestBody Role role, @PathVariable Integer id){
        return roleService.update(role, id);
    }

    @DeleteMapping("/role/{id}")
    public Role delte(@PathVariable Integer id){
        return roleService.delete(id);
    }

    @PostMapping("/users/{id}/role")
    public Role createUserHasRole(@RequestBody Role role,@PathVariable Integer id){
        return roleService.createUserHasRole(role, id);
    }

    @PostMapping("/users/{userId}/role/{roleId}")
    public User addUserHasRole(@PathVariable Integer userId, @PathVariable Integer roleId){
        return roleService.addUserHasRole(userId, roleId);
    }

    @GetMapping("/roles/{id}/users")
    public List<User> getAllUsersByRoleId(@PathVariable Integer id){
        return roleService.getAllUsersByRoleId(id);
    }

    @GetMapping("/users/{id}/roles")
    public List<Role> getAllRolesByUserId(@PathVariable Integer id){
        return roleService.getAllRolesByUserId(id);
    }
}
