package id.co.mii.serverapp.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameOrEmployeeEmail(String username, String email);
    Optional<User> findByToken(String token);
    @Query("SELECT u FROM User u WHERE u.token = ?1")
    public User findByTokenJPQL (String token);
}
