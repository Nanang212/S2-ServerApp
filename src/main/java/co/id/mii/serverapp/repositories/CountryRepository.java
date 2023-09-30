package co.id.mii.serverapp.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.mii.serverapp.models.Country;

//untuk query

public interface CountryRepository extends JpaRepository<Country, Integer> {
    
    boolean existsByName(String name); //sql method

    public List<Country> findByNameOrRegionName(String name, String regionName);

    
}
 