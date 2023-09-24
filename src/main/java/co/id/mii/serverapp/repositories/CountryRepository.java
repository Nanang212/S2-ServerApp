package co.id.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.mii.serverapp.models.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    
    boolean existsByName(String name);

}
