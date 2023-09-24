package co.id.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.mii.serverapp.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

}
