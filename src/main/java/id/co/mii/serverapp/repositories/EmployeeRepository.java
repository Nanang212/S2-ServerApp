package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.dto.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}