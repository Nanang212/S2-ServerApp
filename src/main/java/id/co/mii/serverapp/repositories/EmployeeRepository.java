package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmail(String email);

    Employee findByName(String name);
}
