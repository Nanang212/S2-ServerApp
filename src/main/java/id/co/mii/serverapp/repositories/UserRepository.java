package id.co.mii.serverapp.repositories;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   //public Optional<User> findByName(String name); 

   public boolean existsByUsername (String username);
}
