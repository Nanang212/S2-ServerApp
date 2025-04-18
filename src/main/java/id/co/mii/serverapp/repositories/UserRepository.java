package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsernameOrEmployeeEmail(String username, String email);
}
