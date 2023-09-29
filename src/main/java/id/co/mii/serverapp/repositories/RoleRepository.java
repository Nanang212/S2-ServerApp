package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Set<Role> findAllByNameIgnoreCaseIn(Collection<String> name);

    boolean existsByNameIgnoreCase(String name);
}
