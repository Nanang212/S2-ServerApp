package co.id.mii.serverapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.mii.serverapp.models.Role;
import co.id.mii.serverapp.services.RoleServices;
import lombok.AllArgsConstructor;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private RoleServices roleServices;

    @GetMapping
    public List<Role> findAll() {
        return roleServices.getall();
    }

    @GetMapping("/{Id}")
    public Role findById(@PathVariable Integer Id) {
        return roleServices.getById(Id);
    }

    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleServices.create(role);
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Integer id, @RequestBody Role role) {
        return roleServices.update(id, role);
    }

    @DeleteMapping("/{id}")
    public Role delete(@PathVariable Integer id) {
        return roleServices.delete(id);
    }
}
