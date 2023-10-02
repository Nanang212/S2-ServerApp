package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Role;

public interface RoleRepositories extends JpaRepository<Role, Integer>{
    
}
