package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Boolean existsByCodeIgnoreCase(String name);

    Boolean existsByNameIgnoreCase(String name);
}
