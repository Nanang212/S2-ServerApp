package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.controllers.base.BaseController;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepo;
import id.co.mii.serverapp.services.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController extends BaseController<Region> {
}
