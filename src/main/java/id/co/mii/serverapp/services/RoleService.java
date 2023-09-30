package id.co.mii.serverapp.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.RoleRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;


@Service
@AllArgsConstructor
@Data
public class RoleService {

    private RoleRepository roleRepository;

    private UserService userService;

    private UserRepository userRepository;

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role getById(Integer id){
        return roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id does not exists"));
    }

    public Role create(Role role){
        return roleRepository.save(role);
    }

    public Role update(Role role, Integer id){
        getById(id);
        role.setId(id);
        return create(role);
    }

    public Role delete(Integer id){
        Role role = getById(id);getById(id);
        roleRepository.delete(role);
        return role;
    }

    public Role createUserHasRole(Role role, Integer id){
       
       User user = userService.getById(id);

       List<Role> userHasRole = Arrays.asList(role);

       user.setRoles(userHasRole);

         return roleRepository.save(role); 
    }

    public User addUserHasRole (Integer userId, Integer roleId){
        User user = userService.getById(userId);
        Role role = getById(roleId);

        List<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public List<User> getAllUsersByRoleId(Integer id){
        userService.getById(id);
        return userRepository.findUsersByRolesId(id);
    }

    public List<Role> getAllRolesByUserId(Integer id){
        getById(id);
        return roleRepository.findRolesByUsersId(id);
    }

    public User updateRoleOfUser(Role role ,Integer id){
        User user = userService.getById(id);

        List<Role> userHasRoles = user.getRoles();
      
        for (Role updateRole : userHasRoles) {

            if(updateRole.getId().equals(role.getId())){
                updateRole.setId(role.getId());
                updateRole.setName(role.getName());
            }
        }

        user.setRoles(userHasRoles);

        return userRepository.save(user);
    }
 
}
