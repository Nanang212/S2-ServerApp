package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsernameIgnoreCase(String username);

    Optional<User> findByUsernameOrEmployeeEmail(String name, String email);
}
