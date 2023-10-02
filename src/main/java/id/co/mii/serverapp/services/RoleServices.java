package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;

// import id.co.mii.serverapp.models.User;
// import id.co.mii.serverapp.models.dto.RoleDTO;
// import id.co.mii.serverapp.models.request.CreateRoleRequest;
import id.co.mii.serverapp.models.request.RoleRequest;
import id.co.mii.serverapp.repositories.RoleRepositories;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServices {
    private RoleRepositories roleRepositories;

    public Role create(RoleRequest request) {

        Role role = new Role();
        role.setName(request.getName());

        return roleRepositories.save(role);
    }

    public Role getById(Integer roleId) {
        return roleRepositories
            .findById(roleId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }

    public List<Role> getAll() {
        return roleRepositories.findAll();
    }

    public Role update(Integer roleId, RoleRequest request) {
        Role role = getById(roleId);
        role.setName(request.getName());
        return roleRepositories.save(role);
    }

    public Role delete(Integer roleId) {
        Role role = roleRepositories
            .findById(roleId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
        roleRepositories.delete(role);
        return role;
    }
}
