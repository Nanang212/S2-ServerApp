package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    Optional<Privilege> findByNameIgnoreCase(String name);

    Set<Privilege> findByNameIn(Set<String> names);
}
