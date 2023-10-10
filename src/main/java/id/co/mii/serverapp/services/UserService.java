package id.co.mii.serverapp.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository
                .findAll();
    }

    public User getById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // add role
    public User addRole(Integer id, Role role) {
        // untuk cek user
        User user = getById(id);

        // untuk cek role dan set role
        List<Role> roles = user.getRoles();
        roles.add(roleService.getById(role.getId()));
        user.setRoles(roles);
        return userRepository.save(user);
    } 

     public User update(Integer id, RegistrationRequest registrationRequest) {
        User updatedUser = getById(id);
        if (registrationRequest.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username harus diisi");
        }
        if (registrationRequest.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password harus diisi");
        }
        if (registrationRequest.getPhone().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone harus diisi");
        }
        updatedUser.setUsername(registrationRequest.getUsername());
        updatedUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        updatedUser.getEmployee().setPhone(registrationRequest.getPhone());
        updatedUser.setIsEnabled(true);
        updatedUser.setToken(null);
        return userRepository.save(updatedUser);
    }
    public User findByToken(String token) {
        return userRepository
                .findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token is not valid"));
    }

    public boolean verify(String token) {
        User user = userRepository.findByTokenJPQL(token);
         
        if (user == null || user.getIsEnabled()) {
            return false;
        } else {
            return true;
        }
    }
}
