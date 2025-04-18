package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.services.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

  private UserService userService;

  @GetMapping
  public List<User> getAll() {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable Integer id) {
    return userService.getById(id);
  }

  @PutMapping("/{id}")
  public User update(@PathVariable Integer id, @RequestBody User user) {
    return userService.update(id, user);
  }

  // add role
  @PutMapping("/add-role/{id}")
  public User addRole(@PathVariable Integer id, @RequestBody Role role) {
    return userService.addRole(id, role);
  }
}
