package co.id.ms.mii.serverapp.repositories;

import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByUser(User user);

    Optional<Employee> findByUserUsernameIgnoreCase(String name);

    Optional<Employee> findByUserToken(String token);
}
