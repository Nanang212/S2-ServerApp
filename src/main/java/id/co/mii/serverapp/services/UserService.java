package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.UserRequest;
import id.co.mii.serverapp.repositories.RoleRepository;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Validator validator;
    private final RoleRepository roleRepository;

    public User create(UserRequest request) {
        Set<ConstraintViolation<UserRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        User user = new User();
        return save(request, user);
    }

    public User getById(Integer userId) {
        return userRepository
            .findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User update(Integer userId, UserRequest request) {
        Set<ConstraintViolation<UserRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        User user = getById(userId);

        return save(request, user);
    }

    private User save(UserRequest request, User user) {
        Set<Role> roles = roleRepository.findAllByNameIgnoreCaseIn(request.getRoles());

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User delete(Integer userId) {
        User user = userRepository
            .findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userRepository.delete(user);

        return user;
    }
}
