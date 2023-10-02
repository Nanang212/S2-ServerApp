package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.repositories.RoleRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getById(Integer id) {
        return roleRepository.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found!"));
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    
    public Optional<Role> findRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    
    public Role updateRole(Integer id, Role role) {
        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }

   
    public void deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
    }

    
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
