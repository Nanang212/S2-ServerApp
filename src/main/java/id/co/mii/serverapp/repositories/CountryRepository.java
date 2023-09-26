package id.co.mii.serverapp.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.mii.serverapp.models.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "SELECT COUNT(*) FROM tb_country WHERE country_name = ?", nativeQuery = true)
    BigInteger countByName(@Param("name") String name );
}
