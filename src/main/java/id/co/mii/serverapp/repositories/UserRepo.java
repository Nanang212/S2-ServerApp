package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends BaseRepository<User> {
  Boolean existsByUsername(String username);

  Optional<User> findByUsernameOrEmployeeEmail(String username, String email);

  Optional<User> findByToken(String token);
}
