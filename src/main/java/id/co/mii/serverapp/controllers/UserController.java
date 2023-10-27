package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getAll());
  }

//  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable Integer id) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.update(id, userRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> delete(@PathVariable Integer id) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.delete(id));
  }

  @PutMapping("/{id}/add-role")
  public ResponseEntity<User> addRole(@PathVariable Integer id, @RequestBody Role role) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.addRole(id, role));
  }
}
