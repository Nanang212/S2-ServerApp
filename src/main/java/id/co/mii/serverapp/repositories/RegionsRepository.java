package id.co.mii.serverapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.mii.serverapp.models.Region;



public interface RegionsRepository extends JpaRepository<Region, Integer>{

    // query method
    public Boolean existsByName(String name);

    public Optional<Region> findByName(String name);

    //jpql
    @Query(
        value = "SELECT name FROM Region "
    )
    public List<String> getAllRegionName();

    //native
    @Query(
        value = "SELECT * FROM tb_region WHERE region_name LIKE :name",
        nativeQuery = true
    )
    public List<Region> searchByName(@Param("name") String name);
    
}
