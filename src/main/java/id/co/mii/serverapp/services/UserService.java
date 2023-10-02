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
    private RoleService roleService;
    private ModelMapper modelMapper;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public User create(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already used");
        }
        User user = modelMapper.map(userRequest, User.class);
        List<Role> roles = userRequest.getRoleIds().stream()
                .map(roleId -> {
                    // roleService.getById(roleId)
                    Role role = roleService.getById(roleId);
                    return role;
                })
                .collect(Collectors.toList());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User update(Integer id, User user) {
        User userdb = getById(id);
        user.setId(id);
        if (!userdb.getUsername().equalsIgnoreCase(user.getUsername())) {
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already used");
            }
        }
        return userRepository.save(user);
    }

    public User delete(Integer id) {
        User user = getById(id);
        user.getRoles().forEach(role -> role.getUsers().remove(user));
        userRepository.delete(user);
        return user;
    }
}
