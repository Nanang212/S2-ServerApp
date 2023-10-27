package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.repositories.EmployeeRepository;
import co.id.ms.mii.serverapp.services.EmployeeService;
import co.id.ms.mii.serverapp.services.UserProfileService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/profile")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class EmployeeProfileController {
    private final UserProfileService userProfileService;

    @Autowired // Add this annotation to properly inject the service
    public EmployeeProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{name}")
    @ResponseBody
    public Employee getEmployeesByIdAndName(@PathVariable String name) {
            return userProfileService.findByName(name);
    }

    @PutMapping("/{name}")
    public Employee updateEmployeeProfile(@PathVariable String name, @RequestBody UserRequest userRequest){
        return userProfileService.updateEmployeeProfile(name,userRequest);
    }

}
