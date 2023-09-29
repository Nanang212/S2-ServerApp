package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends BaseRepository<User> {
    Boolean existsByUsername(String username);
}
