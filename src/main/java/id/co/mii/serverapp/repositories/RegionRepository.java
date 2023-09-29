package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Region;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
  
  // Native
  @Query(
    value = "SELECT * FROM tb_region WHERE region_name LIKE ?1", // (?)parameterized query untuk mengetahui posisi digunakan angka dibelakangnya
    nativeQuery = true // penambahan boolean
  )
  public List<Region> searchAllNameNative(String name);

  // JPQL
  @Query(
    "SELECT r FROM Region r WHERE r.name LIKE :name" // name parameter harus menggunakan alias
  )
  public List<Region> searchAllNameJPQL(@Param("name") String name);

  @Query("SELECT name FROM Region")
  public List<String> getAllName();

  // Query Method
  public Optional<Region> findByName(String name);

}
