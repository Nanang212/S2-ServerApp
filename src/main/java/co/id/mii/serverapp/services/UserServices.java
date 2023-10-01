package co.id.mii.serverapp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.id.mii.serverapp.models.Role;
import co.id.mii.serverapp.models.User;
import co.id.mii.serverapp.models.dto.request.UserRequest;
import co.id.mii.serverapp.repositories.EmployeeRepository;
import co.id.mii.serverapp.repositories.RoleRepository;
import co.id.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServices {
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private RoleServices roleServices;

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getById(Integer id){
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"id User is not found!!!")
        );
    }
    public User create (UserRequest userRequest){
        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username already exists!!!");
        }

            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());

            List<Role> userRolesList = new ArrayList<Role>();

            //save role
            for (String roleName :
                    userRequest.getRolesname()) {
                if(roleRepository.existsByName(roleName)){
                    //find name in role table
                    Role findRolename = roleRepository.findByNameContainingIgnoreCase(roleName);
                    userRolesList.add(findRolename);
                } else{
                    // save new name in role table
                    Role userRole = new Role();

                    userRole.setName(roleName);

                    roleRepository.save(userRole);
                    userRolesList.add(userRole);
                }
            }
            user.setRoles(userRolesList);

            //save user
            userRepository.save(user);

        return user;
    }

    public User update(UserRequest userRequest, Integer id){
        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Name User already exists!!!");
        }
        // get user that want to update by id
        User getuser = getById(id);

        getuser.setUsername(userRequest.getUsername());
        getuser.setPassword(userRequest.getPassword());

        List<Role> userRolesList = new ArrayList<Role>();

        //save role
        for (String roleName :
                userRequest.getRolesname()) {
            if(roleRepository.existsByName(roleName)){
                //find name in role table
                Role findRolename = roleRepository.findByNameContainingIgnoreCase(roleName);
                userRolesList.add(findRolename);
            } else{
                // save new name in role table
                Role userRole = new Role();

                userRole.setName(roleName);

                roleRepository.save(userRole);
                userRolesList.add(userRole);
            }
        }
        getuser.setRoles(userRolesList);

        return userRepository.save(getuser);
    }
    public User delete(Integer id) {
        User user = getById(id);
        user.getRoles().forEach(role -> role.getUsers().remove(user));
        userRepository.delete(user);
        return user;
    }
}
