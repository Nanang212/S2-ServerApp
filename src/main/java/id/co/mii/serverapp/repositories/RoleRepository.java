package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    //spring data JPA untuk mengakses si role
}
