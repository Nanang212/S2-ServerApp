package co.id.ms.mii.serverapp.repositories;

import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    boolean existsByUser(User user);
}
