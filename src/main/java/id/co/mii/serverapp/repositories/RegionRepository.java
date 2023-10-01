package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    boolean existsByNameIgnoreCase(String name);

    // Native
    @Query(
        value = "SELECT * FROM region WHERE region.name LIKE ?1",
        nativeQuery = true
    )
    List<Region> searchAllNameNative(String name);

    // JPQL
    @Query(
        "SELECT r FROM Region r WHERE r.name LIKE :name"
    )
    List<Region> searchAllNameJPQL(@Param("name") String name);

    @Query("SELECT name FROM Region")
    List<String> getAllName();
}
