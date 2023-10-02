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

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.services.RoleService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    private RoleService roleService;

    @GetMapping
    public List<Role> getAll(){
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable Integer id){
        return roleService.getById(id);
    }

    @PostMapping
    public Role insert(@RequestBody Role role){
        return roleService.insert(role);
    }

    @PutMapping("/update/{id}")
    public Role update(@PathVariable Integer id, @RequestBody Role role){
        return roleService.update(id, role);
    }

    @DeleteMapping("/delete/{id}")
    public Role delete(@PathVariable Integer id){
        return roleService.delete(id);
    }
}
