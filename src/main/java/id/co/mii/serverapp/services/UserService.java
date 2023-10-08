package id.co.mii.serverapp.services;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.UserRepositorty;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepositorty userRepository;
  private RoleService roleService;


  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!")
      );
  }
  //  public User create(User user) {
  //   return userRepository.save(user);
  // }
  
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
    roles.add(roleService.getById(role.getId()));
    user.setRoles(roles);

    return userRepository.save(user);
  }
}