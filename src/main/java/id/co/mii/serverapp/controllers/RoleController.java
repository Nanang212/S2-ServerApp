package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.dto.requests.RoleRequest;
import id.co.mii.serverapp.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping(
        value = "/role",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Role create(@RequestBody RoleRequest request) {
        return roleService.create(request);
    }

    @GetMapping(
        value = "/role/{roleId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Role getById(@PathVariable Integer roleId) {
        return roleService.getById(roleId);
    }

    @GetMapping(
        value = {"/role", "/roles"},
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @PutMapping(
        value = "/role/{roleId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Role update(@PathVariable Integer roleId, @RequestBody RoleRequest request) {
        return roleService.update(roleId, request);
    }

    @DeleteMapping(
        value = "/role/{roleId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Role delete(@PathVariable Integer roleId) {
        return roleService.delete(roleId);
    }
}
