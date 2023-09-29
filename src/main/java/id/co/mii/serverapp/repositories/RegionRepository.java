package id.co.mii.serverapp.repositories;




import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    
    // Region findByName(String name);

    // Native
    @Query(
        value = "SELECT * FROM regions WHERE name LIKE ?1", // parameterized query = position base parameter
        nativeQuery = true
    )
    public List<Region> searchAllNameNative(String name);

    // JPQL
    @Query(
        "SELECT r FROM Region r WHERE r.name LIKE :name" // name parameter
    )
    public List<Region> searchAllNameJPQL(@Param("name") String name);

    @Query("SELECT name FROM Region")
    public List<String> getAllName();

    // Query Method
    public Optional<Region> findByName(String name);

}
