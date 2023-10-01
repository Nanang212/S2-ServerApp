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
        return roleRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Role is not found !!!"));
    }

    public Role create(Role role) {

        if(roleRepository.existsByName(role.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Role name already exists!!!");
        }

        return roleRepository.save(role);
    }

    public Role update(Integer id, Role role) {

        if(roleRepository.existsByName(role.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Role name already exists!!!");
        }

        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }

    public Role delete(Integer id) {
        Role role = getById(id);
        //perulangan untuk memilih user yang roles nya akan dihapus
        role.getUsers().forEach(user -> user.getRoles().remove(role));
        roleRepository.delete(role);
        return role;
    }

}
