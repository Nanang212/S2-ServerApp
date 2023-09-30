package co.id.ms.mii.serverapp.repositories;

import co.id.ms.mii.serverapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    boolean existsByName(String name);
    Role findByNameContainingIgnoreCase(String name);
}
