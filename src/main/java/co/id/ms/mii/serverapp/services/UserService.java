package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.controllers.EmployeeController;
import co.id.ms.mii.serverapp.dto.request.EmployeeRequest;
import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.models.*;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.repositories.EmployeeRepository;
import co.id.ms.mii.serverapp.repositories.RoleRepository;
import co.id.ms.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;

    public List<User> getall(){
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
                    userRequest.getRolesName()) {
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
                userRequest.getRolesName()) {
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
        userRepository.delete(user);
        return user;
    }
}
