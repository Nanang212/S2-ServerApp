package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.mii.serverapp.models.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    // menggunakan cara JPQL untuk membuat query custom
    @Query("SELECT COUNT(c) FROM Country c WHERE c.region.name = :regionName AND c.name = :countryName")
    int countRegionAndCountryName(@Param("regionName") String regionName, @Param("countryName") String countryName);
}
