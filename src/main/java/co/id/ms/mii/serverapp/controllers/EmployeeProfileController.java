package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.dto.request.ChangePasswordRequest;
import co.id.ms.mii.serverapp.dto.request.UserRequest;
import co.id.ms.mii.serverapp.models.Employee;
import co.id.ms.mii.serverapp.services.EmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/profile")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class EmployeeProfileController {
    private final EmployeeProfileService employeeProfileService;

    @Autowired // Add this annotation to properly inject the service
    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @GetMapping("/{name}")
    @ResponseBody
    public Employee getEmployeesByIdAndName(@PathVariable String name) {
            return employeeProfileService.findByName(name);
    }

    @PutMapping("/{name}")
    public Employee updateEmployeeProfile(@PathVariable String name, @RequestBody UserRequest userRequest){
        return employeeProfileService.updateEmployeeProfile(name,userRequest);
    }

    @PutMapping("/change-password/{name}")
    public Employee changePassword(@PathVariable String name, @RequestBody ChangePasswordRequest passwordRequest) {
        return employeeProfileService.changePasswordProfile(name, passwordRequest);
    }

}
