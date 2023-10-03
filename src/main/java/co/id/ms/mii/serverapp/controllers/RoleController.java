package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.models.Role;
import co.id.ms.mii.serverapp.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @GetMapping
    public List<Role> findAll() {
        return roleService.getall();
    }

    @GetMapping("/{Id}")
    public Role findById(@PathVariable Integer Id){
        return roleService.getById(Id);
    }

    @PostMapping
    public Role create(@RequestBody Role role){
        return roleService.create(role);
    }

    @PutMapping("/{id}")
    public Role update(@RequestBody Role role, @PathVariable Integer id){
        return roleService.update(role,id);
    }

    @DeleteMapping("/{id}")
    public Role delete(@PathVariable Integer id){
        return roleService.delete(id);
    }
}
