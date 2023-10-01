package id.co.mii.serverapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private ModelMapper modelMapper;

    public List<User> getAll() {
        return userRepository
            .findAll();
    }

    public User getById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // create with dto model mapper
    public User create(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);

        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "userName is already exists!!!");
        }

        List<Role> roles = userRequest.getRolesId().stream().map(roleId -> roleService.getById(roleId))
                .collect(Collectors.toList());
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    // update
    public User update(Integer id, UserRequest userRequest) {
        // find id
        User updatedUser  = getById(id);
        // set data
        updatedUser.setUsername(userRequest.getUsername());
        updatedUser.setPassword(userRequest.getPassword());
        updatedUser.setRoles(userRequest.getRolesId().stream().map(roleId -> roleService.getById(roleId))
                .collect(Collectors.toList()));
        userRepository.save(updatedUser);
        if (updatedUser.getUsername() != userRequest.getUsername()){
            if (userRepository.existsByUsername(updatedUser.getUsername())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists!");
            }
        }
        return updatedUser;
    }

    // seach by name
    //JPQL
    public List<User> search(String name){
        if(userRepository.searchAllName("%" + name + "%").isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User does not exists!!!");
        }
        return userRepository.searchAllName("%" + name + "%");
    }

    //delete
    public User delete(Integer id){
        User user = getById(id);
        user.getRoles().forEach(role -> role.getUsers().remove(user));
        userRepository.delete(user);
        return user;
    } 

}
