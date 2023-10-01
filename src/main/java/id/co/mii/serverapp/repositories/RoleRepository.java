package id.co.mii.serverapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.mii.serverapp.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Optional<Role> findByName(String name);
    boolean existsByName(String name);

    // JPQL
    @Query(
        "SELECT r FROM Role r WHERE r.name LIKE :name" // name parameter
    )
    public List<Role> searchAllName(@Param("name") String name);
}
