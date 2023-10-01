package id.co.mii.serverapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.mii.serverapp.models.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    // Query Method
    public Optional<Employee> findByName(String name);
    boolean existsByName(String name);

    // JPQL
    @Query(
        "SELECT e FROM Employee e WHERE e.name LIKE :name" // name parameter
    )
    public List<Employee> searchAllName(@Param("name") String name);
}
