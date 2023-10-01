package id.co.mii.serverapp.services;

import java.util.List;
// import java.util.stream.Collectors;

// import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.RoleRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;


        public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role getById(Integer id){
        return roleRepository
            .findById(id)
            .orElseThrow(()->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found")
            );
    }
    
    public Role create(Role role){
        if(roleRepository.findByName(role.getName()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role is already exists!!!");
        }
        return roleRepository.save(role);
    }


    
    public Role update(Integer id,Role role){
        // find id
        getById(id);
         // set data
        role.setId(id);
        if (roleRepository.existsByName(role.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exists!");
        }
        if (userRepository.existsByUsername(role.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role can't be the same as username!");
        }
        if (employeeRepository.existsByName(role.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role can't be the same as employee name!");
        }
        return roleRepository.save(role);     
    }

    // seach by name
    //JPQL
    public List<Role> search(String name){
        if(roleRepository.searchAllName("%" + name + "%").isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role does not exists!!!");
        }
        return roleRepository.searchAllName("%" + name + "%");
    }

    //delete
    public Role delete(Integer id){
        Role role = getById(id);
        role.getUsers().forEach(user -> user.getRoles().remove(role));
        roleRepository.delete(role);
        return role;
    } 
}
