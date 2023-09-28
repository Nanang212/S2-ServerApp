package co.id.ms.mii.serverapp.repositories;

import co.id.ms.mii.serverapp.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CountryRepository extends JpaRepository<Country, Integer> {

    boolean existsByName(String name);
    public List<Country> findByNameOrRegionName(String name, String regionName);

}
