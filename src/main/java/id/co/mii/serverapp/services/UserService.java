package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.repositories.RoleRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }

    public User create(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        Role role = roleRepository.findById(userRequest.getRoleId())
        .orElseThrow(() -> new IllegalArgumentException("Role with the provided ID not found."));
        user.setRoles(Arrays.asList(role));
        // user.addRole(role);
        
        return userRepository.save(user);
    }

    public User update(Integer id, UserRequest updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            Role role = roleRepository.findById(updatedUser.getRoleId())
            .orElseThrow(() -> new IllegalArgumentException("Role with the provided ID not found."));
            user.addRole(role);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Optional<User> delete(Integer id) {
        Optional<User> user = getById(id);
        userRepository.deleteById(id);
        return user;
    }
}
