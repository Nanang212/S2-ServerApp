package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.controllers.EmployeeController;
import co.id.ms.mii.serverapp.dto.request.EmployeeRequest;
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

    public User create (User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username already exists!!!");
        }
        try {

            List<Role> defaultRole = new ArrayList<Role>();
            //save role
            for (Role rolelist :
                    user.getRoles()) {
                if(roleRepository.existsByName(rolelist.getName())){
                    Role findRolename = roleRepository.findByNameContainingIgnoreCase(rolelist.getName());

                    defaultRole.add(findRolename);
                } else{
                    roleRepository.save(rolelist);
                    defaultRole.add(rolelist);
                }
            }
            user.setRoles(defaultRole);

            //save user
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e);
        }

        return user;
    }

    public User update(User user, Integer id){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Name User already exists!!!");
        }
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }
    public User delete(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
}
