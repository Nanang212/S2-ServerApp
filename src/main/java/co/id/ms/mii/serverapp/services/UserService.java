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
        if(userRequest.getUsername().isEmpty() || userRequest.getPassword().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"UserName or Password is Empty!!!");
        }

        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username already exists!!!");
        }
            //make new user obj
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());

            //inisialisasi roletemp untuk menampung data role nanti
            List<Role> RolesTemp = new ArrayList<Role>();

            //save role
            for (String roleName :
                    userRequest.getRolesName()) {
                if(roleRepository.existsByName(roleName)){
                    //find name in role table
                    Role findRolename = roleRepository.findByNameContainingIgnoreCase(roleName);
                    //add to rolesTemp
                    RolesTemp.add(findRolename);
                } else{
                    // save new name in role table
                    Role userRole = new Role();

                    userRole.setName(roleName);

                    roleRepository.save(userRole);
                    RolesTemp.add(userRole);
                }
            }
            user.setRoles(RolesTemp);

            //save user
            userRepository.save(user);

        return user;
    }

    public User update(UserRequest userRequest, Integer id){
        if(userRequest.getUsername().isEmpty() || userRequest.getPassword().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"UserName or Password is Empty!!!");
        }

        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Name User already exists!!!");
        }
        // get user that want to update by id
        User getuser = getById(id);

        getuser.setUsername(userRequest.getUsername());
        getuser.setPassword(userRequest.getPassword());

        //inisialisasi roletemp untuk menampung data role nanti
        List<Role> RolesTemp = new ArrayList<>();

        //save role
        for (String roleName :
                userRequest.getRolesName()) {
            if(roleRepository.existsByName(roleName)){
                //find name in role table
                Role findRolename = roleRepository.findByNameContainingIgnoreCase(roleName);

                //add to rolesTemp
                RolesTemp.add(findRolename);
            } else{
                // save new name in role table
                Role userRole = new Role();

                userRole.setName(roleName);

                roleRepository.save(userRole);
                RolesTemp.add(userRole);
            }
        }
        getuser.setRoles(RolesTemp);

        return userRepository.save(getuser);
    }
    public User delete(Integer id) {
        User user = getById(id);
        user.getRoles().forEach(role -> role.getUsers().remove(user));
        userRepository.delete(user);
        return user;
    }
}
