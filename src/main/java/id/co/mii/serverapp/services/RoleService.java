package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Create a new Role
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Find a Role by ID
    public Optional<Role> findRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    // Update an existing Role
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    // Delete a Role by ID
    public void deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
    }

    // Get all Roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
