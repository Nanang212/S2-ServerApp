package id.co.mii.serverapp.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    boolean existsByNameIgnoreCase(String name);
}
