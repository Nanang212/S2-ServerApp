package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.repositories.UserRepo;
import id.co.mii.serverapp.services.base.BaseService;
import id.co.mii.serverapp.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService extends BaseService<User> {
    private UserRepo userRepo;
    private RoleService roleService;

    public User update(Integer id, UserRequest userRequest) {
        User updatedUser = getById(id);
        if (!StringUtils.isEmptyOrNull(userRequest.getUsername())
                && !updatedUser.getUsername().equalsIgnoreCase(userRequest.getUsername())) {
            if (userRepo.existsByUsername(userRequest.getUsername())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used !!!");
            }
            updatedUser.setUsername(userRequest.getUsername());
        }
        if (!StringUtils.isEmptyOrNull(userRequest.getPassword())
                && !updatedUser.getPassword().equalsIgnoreCase(userRequest.getPassword())) {
            updatedUser.setPassword(userRequest.getPassword());
        }
        if (userRequest.getRoleIds() != null) {
            updatedUser.setRoles(mapToRoles(userRequest.getRoleIds()));
        }
        return userRepo.save(updatedUser);
    }

    @Override
    public User delete(Integer id) {
        User user = getById(id);
        user.getRoles().forEach(role -> role.getUsers().remove(user));
        userRepo.delete(user);
        return user;
    }

    public User addRole(Integer id, Role role) {
        User user = getById(id);
        List<Role> roles = user.getRoles();
        roles.add(roleService.getById(role.getId()));
        user.setRoles(roles);
        return userRepo.save(user);
    }

    private List<Role> mapToRoles(List<Integer> roleIds) {
        return roleIds
                .stream()
                .map(roleId -> roleService.getById(roleId))
                .collect(Collectors.toList());
    }
}
