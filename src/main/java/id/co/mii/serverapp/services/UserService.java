package id.co.mii.serverapp.services;

import java.util.List;
//import java.util.stream.Collectors;

// org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
//import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    //private ModelMapper modelMapper;
    private RoleService roleService;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User not found !!!"));
    }

    // public User create(UserRequest userRequest){

    // if(userRequest.getUsername().isEmpty()
    // || userRequest.getPassword().isEmpty()){
    // throw new ResponseStatusException(
    // HttpStatus.NOT_FOUND,
    // "UserName or Password is Empty!!!");
    // }

    // if(userRepository.existsByUsername(userRequest.getUsername())){
    // throw new ResponseStatusException(
    // HttpStatus.CONFLICT,
    // "Name Employee already exists!!!");
    // }

    // User user = modelMapper.map(userRequest, User.class);
    // List<Role> roles = mapToRoles(userRequest.getRoleIds());
    // user.setRoles(roles);
    // userRepository.save(user);
    // return user;
    // }

    public User update(Integer id, User user) {

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserName or Password is Empty!!!");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name User already exists!!!");
        }

        User updatedUser = getById(id);
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }

    // public User delete(Integer id){
    // User user = getById(id);
    // user.getRoles().forEach(role -> role.getUsers().remove(user));
    // userRepository.delete(user);
    // return user;
    // }

    // add role
    public User addRole(Integer id, Role role) {
        // cek user
        User user = getById(id);

        // cek role & set role
        List<Role> roles = user.getRoles();
        roles.add(roleService.getById(role.getId()));
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
