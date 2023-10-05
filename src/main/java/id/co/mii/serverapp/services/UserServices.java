package id.co.mii.serverapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.EmployeeDTO;
import id.co.mii.serverapp.models.dto.UserDTO;
import id.co.mii.serverapp.models.request.CreateUserRequest;
import id.co.mii.serverapp.models.request.UpdateUserRequest;
import id.co.mii.serverapp.repositories.UserRepositories;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServices {
    private UserRepositories userRepositories;
    private RoleServices roleServices;

    public List<User> getAll() {
        return userRepositories.findAll();
    }

    public User getById(Integer id) {
        return userRepositories
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!"));
    }

    public User update(Integer id, User user) {
        getById(id);
        user.setId(id);
        return userRepositories.save(user);
    }

    // add role
    public User addRole(Integer id, Role role) {
        // cek user
        User user = getById(id);

        // cek role & set role
        List<Role> roles = user.getRoles();
        roles.add(roleServices.getById(role.getId()));
        user.setRoles(roles);

        return userRepositories.save(user);
    }

}
