package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepo extends BaseRepository<Country> {
    Boolean existsByName(String name);
    Boolean existsByCode(String code);
    @Query("SELECT c FROM Country c " +
            "WHERE LOWER(c.code) LIKE %:keyword% " +
            "OR LOWER(c.name) LIKE %:keyword% " +
            "OR LOWER(c.region.name) LIKE %:keyword% " +
            "ORDER BY c.name")
    List<Country> findALlBy(@Param("keyword") String keyword);
    public List<Country> findByNameOrRegionName(String name, String regionName);
}
