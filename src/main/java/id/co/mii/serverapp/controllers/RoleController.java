package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
  private RoleService roleService;

  @PreAuthorize("hasAuthority('CREATE_ADMIN')")
  @PostMapping
  public ResponseEntity<Role> create(@RequestBody Role role) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(roleService.create(role));
  }

  @PreAuthorize("hasAuthority('READ_ADMIN')")
  @GetMapping
  public ResponseEntity<List<Role>> getAll() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(roleService.getAll());
  }

  @PreAuthorize("hasAuthority('READ_ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<Role> getById(@PathVariable Integer id) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(roleService.getById(id));
  }

  @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<Role> update(@PathVariable Integer id, @RequestBody Role role) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(roleService.update(id, role));
  }

  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Role> delete(@PathVariable Integer id) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(roleService.delete(id));
  }
}
