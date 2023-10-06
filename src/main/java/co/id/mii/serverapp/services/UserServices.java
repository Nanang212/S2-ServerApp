package co.id.mii.serverapp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.id.mii.serverapp.models.Role;
import co.id.mii.serverapp.models.User;
import co.id.mii.serverapp.models.dto.request.UserRequest;
import co.id.mii.serverapp.repositories.EmployeeRepository;
import co.id.mii.serverapp.repositories.RoleRepository;
import co.id.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServices {
    private UserRepository userRepository;
    private RoleServices roleServices;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id User is not found!!!"));
    }

    public User update(Integer id, User user) {
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    // add role
    public User addRole(Integer id, Role role) {
        // cek user
        User user = getById(id);

        // cek role & set role
        List<Role> roles = user.getRoles();
        roles.add(roleServices.getById(role.getId()));
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
