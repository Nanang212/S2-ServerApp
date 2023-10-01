package co.id.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.mii.serverapp.models.Employee;
import co.id.mii.serverapp.models.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
    boolean existsByNameIgnoreCase(String nama);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByUser(User user);
}
