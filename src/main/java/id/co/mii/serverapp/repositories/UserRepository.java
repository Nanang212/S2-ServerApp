package id.co.mii.serverapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByUsername(String username);

    Optional<User> findByUsernameOrEmployeeEmail(String username, String email);

}
