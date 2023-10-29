package id.co.mii.serverapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.repositories.RoleRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public User update(Integer id, UserRequest userRequest) {

        User user = getById(id);

        if (userRequest.getPassword().isEmpty() || userRequest.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Password is required!");
        }

        if (userRepository.findByUsername(userRequest.getUsername()).getUsername() != userRequest.getUsername()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exist!");
        }

        if (userRequest.getUsername().contains(" ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username should not contain whitespace!");
        }

        user.setUsername(userRequest.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        List<Role> roles = new ArrayList<>();

        if (!userRequest.getRoles().isEmpty()) {
            for (Role r : userRequest.getRoles()) {
                Role role = roleRepository.findById(r.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!"));
                roles.add(role);
            }
            user.setRoles(roles);
        }

        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User delete(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }

    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
