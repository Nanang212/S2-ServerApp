package id.co.mii.serverapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.mii.serverapp.models.Region;
import java.util.List;


public interface RegionRepository extends JpaRepository<Region, Integer>{
    //query method
    Boolean existsByName(String name);

    //query method
    public Optional<Region> findByName(String name);
    // public List<Region> findByName(String name);

    //jpql
    @Query (
        "SELECT r FROM Region r WHERE r.name LIKE :name"
    )
    public List<Region> searchAllNameJPQL(@Param("name")String name);

    @Query("SELECT name FROM Region")
    public List<String> getAllName();

    //native
    @Query(
        value = "SELECT * FROM tb_region WHERE region_name LIKE ?1", //parameterized query = position base parameter
        nativeQuery = true
    )
    public List<Region> searchAllNameNative(String name);
}
