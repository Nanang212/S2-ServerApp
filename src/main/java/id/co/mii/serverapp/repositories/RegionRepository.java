package id.co.mii.serverapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.mii.serverapp.models.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    // native
    @Query(value = "SELECT * FROM tb_region WHERE region_name LIKE ?1", nativeQuery = true)
    public List<Region> searchAllNameNative(String name);

    // JPQL
    @Query("SELECT r FROM Region r WHERE r.name LIKE :name")
    public List<Region> searchAllNameJPQL(@Param("name") String name);

    @Query("SELECT name FROM Region")
    public List<String> getAllName();

    // Query methode
    public Optional<Region> findByName(String name);
}
