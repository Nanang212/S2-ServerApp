package co.id.ms.mii.serverapp.repositories;

import co.id.ms.mii.serverapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByUsername(String username);

    Optional<User> findByUsernameOrAndEmployee_Email(String username,String email);
}
