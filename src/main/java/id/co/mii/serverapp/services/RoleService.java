package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.repositories.RoleRepo;
import id.co.mii.serverapp.services.base.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class RoleService extends BaseService<Role> {
  private RoleRepo roleRepo;

  @Override
  public Role getById(Integer id) {
    return roleRepo
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));
  }

  @Override
  public Role delete(Integer id) {
    Role role = getById(id);
    role.getUsers().forEach(user -> user.getRoles().remove(role));
    roleRepo.delete(role);
    return role;
  }
}
