package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepo;
import id.co.mii.serverapp.services.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegionService extends BaseService<Region> {
}
