package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.dto.requests.RoleRequest;
import id.co.mii.serverapp.repositories.RoleRepository;
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
public class RoleService {
    private final RoleRepository roleRepository;
    private final Validator validator;

    public Role create(RoleRequest request) {
        Set<ConstraintViolation<RoleRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (roleRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role name already exists");
        }

        Role role = new Role();
        role.setName(request.getName().toUpperCase());

        return roleRepository.save(role);
    }

    public Role getById(Integer roleId) {
        return roleRepository
            .findById(roleId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role update(Integer roleId, RoleRequest request) {
        Set<ConstraintViolation<RoleRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        Role role = getById(roleId);
        role.setName(request.getName().toUpperCase());

        return roleRepository.save(role);
    }

    public Role delete(Integer roleId) {
        Role role = roleRepository
            .findById(roleId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));

        roleRepository.delete(role);

        return role;
    }
}
