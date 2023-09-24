package co.id.ms.mii.serverapp.repositories;

import co.id.ms.mii.serverapp.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CountryRepository extends JpaRepository<Country, Integer> {

    boolean existsByName(String name);
}
