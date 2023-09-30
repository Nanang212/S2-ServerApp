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
    private ModelMapper modelMapper;
    private RoleService roleService;


     public List<User> getAll(){
        return userRepository.findAll();
    }
    
    public User getById(Integer id){
        return userRepository
        .findById(id)
        .orElseThrow(() -> new
        ResponseStatusException(HttpStatus.NOT_FOUND,
        "User not found !!!"));
    }

    public User create(UserRequest userRequest){
        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Name Employee already exists!!!");
        }
        
        User user = modelMapper.map(userRequest, User.class);
        List<Role> roles = mapToRoles(userRequest.getRoleIds());
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    public User update(Integer id, UserRequest userRequest){
        User updatedUser = getById(id);
        updatedUser.setUsername(userRequest.getUsername());
        updatedUser.setPassword(userRequest.getPassword());
        return userRepository.save(updatedUser);
    }

    public User delete(Integer id){
        User user = getById(id);
        user.getRoles().forEach(role -> role.getUsers().remove(user));
        userRepository.delete(user);
        return user;
    }

    private List<Role> mapToRoles(List<Integer> roleIds){
        return roleIds
            .stream()
            .map(roleId -> roleService.getById(roleId))
            .collect(Collectors.toList());
    }
}
