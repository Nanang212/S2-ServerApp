package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Role"));
    }

    public Role create(Role role){
        if(roleRepository.existsByName(role.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exists");
        }
        return roleRepository.save(role);
    }

    public Role update(Integer id, Role role){
         if(roleRepository.existsByName(role.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exists");
        }
        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }

    public Role delete(Integer id){
        Role role = getById(id);
        role.getUsers().forEach(user -> user.getRoles().remove(role));
        roleRepository.delete(role);
        return role;
    }
    
}
