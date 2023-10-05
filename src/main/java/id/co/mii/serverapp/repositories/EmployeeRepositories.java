package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Employee;

public interface EmployeeRepositories extends JpaRepository<Employee, Integer>{

}
