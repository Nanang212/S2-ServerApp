package co.id.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.id.mii.serverapp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public boolean existsByUsername (String username);
}
