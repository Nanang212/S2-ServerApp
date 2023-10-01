package id.co.mii.serverapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.mii.serverapp.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Query Method
    public Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    // JPQL
    @Query(
        "SELECT u FROM User u WHERE u.username LIKE :username" // name parameter
    )
    public List<User> searchAllName(@Param("username") String username);
}
