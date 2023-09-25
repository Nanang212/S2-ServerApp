package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT COUNT(r) > 0 FROM Region r WHERE r.name = :countryName")
    boolean isExistsByName(@Param("countryName") String countryName);

    Country findByName(String name);
    Country findByCode(String name);
}
