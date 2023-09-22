package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    Boolean existsByName (String name);
}
