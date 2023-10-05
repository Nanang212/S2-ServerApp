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

    public List<Role> getAll() {
        return roleRepositories.findAll();
    }

    public Role getById(Integer id) {
        return roleRepositories
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!!!"));
    }

    public Role create(Role role) {
        return roleRepositories.save(role);
    }

    public Role update(Integer id, Role role) {
        getById(id);
        role.setId(id);
        return roleRepositories.save(role);
    }

    public Role delete(Integer id) {
        Role role = getById(id);
        roleRepositories.delete(role);
        return role;
    }
}
