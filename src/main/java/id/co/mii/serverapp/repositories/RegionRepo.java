package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepo extends BaseRepository<Region> {
  Boolean existsByName(String regionName);
}
