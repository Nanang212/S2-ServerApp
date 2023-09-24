package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import id.co.mii.serverapp.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT COUNT(c) > 0 FROM Country c WHERE c.region.name = :regionName AND c.name = :countryName")
    boolean existsByName(@Param("regionName") String regionName, @Param("countryName") String countryName);
}
