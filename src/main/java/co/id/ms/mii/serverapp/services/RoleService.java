package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.models.Role;
import co.id.ms.mii.serverapp.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;


    public List<Role> getall(){
        return roleRepository.findAll();
    }

    public Role getById(Integer id){
        return roleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"id Role is not found!!!")
        );
    }

    public Role create (Role role){
        if(role.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Role name is Empty!!!");
        }

        if(roleRepository.existsByName(role.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Role name already exists!!!");
        }

        return roleRepository.save(role);
    }
    public Role update(Role role, Integer id){
        if(role.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Role name is Empty!!!");
        }

        if(roleRepository.existsByName(role.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Role name already exists!!!");
        }

        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }
    public Role delete(Integer id) {
        Role role = getById(id);
        role.getUsers().forEach(users -> users.getRoles().remove(role));
        roleRepository.delete(role);
        return role;
    }
}
