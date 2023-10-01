package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    boolean existsByUsername (String username);
    
} 
