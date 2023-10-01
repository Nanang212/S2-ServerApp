package id.co.mii.serverapp.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Boolean existsByName (String name);//query method

    //native
   // Native
   //native digunakan untuk mengenerate query yang dimana akan dikembalikan objek dengan keyword String findAll()
   //JPQL Query digunakan untuk mengembalikan objek yang secara langsung
   //Query Method ??
   @Query(
    value = "SELECT * FROM tb_region WHERE region_name LIKE ?1", // parameterized query = position base parameter
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
 
}
 
    //kenapa List error dikarenakan parameter dengan argumen??
    //kenapa didalam RegionRepository dan countryService harus menggunakan native,JPQL?

    
    