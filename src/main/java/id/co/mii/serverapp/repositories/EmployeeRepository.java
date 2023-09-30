package id.co.mii.serverapp.repositories;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // public Optional<Employee> findByName(String name);

     boolean existsByName(String name);
     boolean existsByEmail(String email);
     boolean existsByUser(User User);

}
