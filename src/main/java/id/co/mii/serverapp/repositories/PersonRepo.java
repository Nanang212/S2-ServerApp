package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Integer> {
}
