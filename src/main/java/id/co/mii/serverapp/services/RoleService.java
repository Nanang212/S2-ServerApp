package id.co.mii.serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.RoleRepository;
import id.co.mii.serverapp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    // // Create Role
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Read Role by ID
    public Role getRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.orElse(null);
    }

    // Read All Roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Update Role
    public Role updateRole(Long id, Role newRole) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isPresent()) {
            Role existingRole = roleOptional.get();
            existingRole.setName(newRole.getName());
            return roleRepository.save(existingRole);
        }

        return null;
    }

    // Delete Role
    public Role deleteRole(Long id) {
        Role role = getRoleById(id);
        roleRepository.deleteById(id);
        return role;
    }
    public User addUserRole (Long userId,Long roleId){
        User user = userRepository.findById(userId).orElse(null);
        Role role = getRoleById(roleId);
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
