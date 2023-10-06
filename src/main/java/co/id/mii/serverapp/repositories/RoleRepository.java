package co.id.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.id.mii.serverapp.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // boolean existsByName(String name);
    // Role findByNameContainingIgnoreCase(String name);
}
