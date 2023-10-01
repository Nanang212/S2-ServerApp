package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import id.co.mii.serverapp.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
