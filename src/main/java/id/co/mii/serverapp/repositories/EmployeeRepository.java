package id.co.mii.serverapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Optional<Employee> findByUuid(String uuid);
}
