package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.dto.User;

public interface UserRepositorty extends JpaRepository<User,Integer> {
    
}
