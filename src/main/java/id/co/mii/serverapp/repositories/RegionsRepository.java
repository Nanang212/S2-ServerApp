package id.co.mii.serverapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Region;



public interface RegionsRepository extends JpaRepository<Region, Integer>{
    Boolean existsByName(String name);

    Optional<Region> findByName(String name);
}
