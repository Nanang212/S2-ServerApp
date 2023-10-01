package id.co.mii.serverapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Create Role
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    // Read Role by ID
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    // Read All Roles
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    // Update Role
    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    // Delete Role
    @DeleteMapping("/{id}")
    public Role deleteRole(@PathVariable Long id) {
       return roleService.deleteRole(id);
    }
    @PostMapping("/users/{userId}/role/{roleId}")
    public User addUserRole (@PathVariable Long userId , @PathVariable Long roleId){
        return roleService.addUserRole(userId, roleId);
    }
}
