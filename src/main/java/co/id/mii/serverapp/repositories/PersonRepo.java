package co.id.mii.serverapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import co.id.mii.serverapp.models.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {
    
}
