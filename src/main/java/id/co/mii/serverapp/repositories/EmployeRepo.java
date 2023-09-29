package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepo extends BaseRepository<Employee> {
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
}
