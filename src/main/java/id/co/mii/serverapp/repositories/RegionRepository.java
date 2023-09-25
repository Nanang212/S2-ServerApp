package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.co.mii.serverapp.models.Region;
import java.util.List;


@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {  

    Region findByName(String name);
}
