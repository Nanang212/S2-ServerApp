package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Country;


public interface CountryRepository extends JpaRepository<Country, Integer> {

    Boolean existsByName(String name);

    
}
