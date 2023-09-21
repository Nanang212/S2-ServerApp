package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {
    
}
