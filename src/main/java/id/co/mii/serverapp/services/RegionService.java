package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepo;
import id.co.mii.serverapp.services.base.BaseService;
import id.co.mii.serverapp.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionService extends BaseService<Region> {
    private RegionRepo regionRepo;
    @Override
    public Region create(Region region) {
        if (regionRepo.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name is already used");
        }
        return regionRepo.save(region);
    }

    @Override
    public Region update(Integer id, Region region) {
        Region updatedRegion = getById(id);
        if (!StringUtils.isEmptyOrNull(region.getName())
                && updatedRegion.getName().equalsIgnoreCase(region.getName())) {
            if (regionRepo.existsByName(region.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Region name is already used");
            }
            updatedRegion.setName(region.getName());
        }
        return regionRepo.save(updatedRegion);
    }
}
