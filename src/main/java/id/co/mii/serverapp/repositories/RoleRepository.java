package id.co.mii.serverapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Role;

public interface RoleRepository extends JpaRepository<Role ,Integer>{
    
    List<Role> findRolesByUsersId(Integer id);
}
