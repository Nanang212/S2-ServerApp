package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepository;

@Service
public class RegionService {
    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    public Region getById(Integer id) {
        return regionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak ditemukan id no :" + id));
    }

    public Region insertData(Region region) {
         if (regionRepository.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama wilayah sudah ada !!! ");
        }

        if (region == null || region.getName() == null || region.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nama wilayah Harus Diisi");
        }

        try {
            return regionRepository.save(region);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Gagal menyimpan Region.");
        }
    }

    public Region update(Integer id, Region region) {
         if (regionRepository.existsByName(region.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nama wilayah sudah ada !!! ");
        }

        getById(id);
        region.setId(id);
        return regionRepository.save(region);
    }

    public Region Delete(Integer id) {
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }
}
