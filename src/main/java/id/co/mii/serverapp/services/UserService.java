package id.co.mii.serverapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.UserRequest;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No User"));
    }

    public User create(UserRequest userRequest) {
        if (userRequest.getUsername().isEmpty() || userRequest.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Both should not be empty");
        }

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }

        User user = modelMapper.map(userRequest, User.class);
        List<Role> roles = mapsToRoles(userRequest.getRolesId());
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    public User update(Integer id, UserRequest userRequest) {
        if (userRequest.getUsername().isEmpty() || userRequest.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Both should not be empty");
        }

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }
        User user = getById(id);
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRoles(mapsToRoles(userRequest.getRolesId()));
        return userRepository.save(user);
    }

    public User delete(Integer id){
        User user = getById(id);
        user.getRoles().forEach(role -> role.getUsers().remove(user));
        userRepository.delete(user);
        return user;
    }

    private List<Role> mapsToRoles(List<Integer> rolesId) {
        return rolesId.stream().map(roleId -> roleService.getById(roleId)).collect(Collectors.toList());
    }

}
