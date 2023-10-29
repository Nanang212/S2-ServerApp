package id.co.mii.serverapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.ChangePasswordRequest;
import id.co.mii.serverapp.models.dto.requests.UserRequest;
import id.co.mii.serverapp.services.EmployeeProfileService;
import lombok.AllArgsConstructor;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/profile")
@PreAuthorize("hasAnyRole('ADMIN', USER)")
public class EmployeeProfileController {
    private final EmployeeProfileService employeeProfileService;

    @GetMapping("/{name}")
    @ResponseBody
    public Employee getEmployeesByIdAndName(@PathVariable String name) {
        return employeeProfileService.findByName(name);
    }

    @PutMapping("/{name}")
    public Employee updateEmployeeProfile(@PathVariable String name, @RequestBody UserRequest userRequest) {
        return employeeProfileService.updateEmployeeProfile(name, userRequest);
    }

    @PutMapping("/change-password/{name}")
    public Employee changePassword(@PathVariable String name, @RequestBody ChangePasswordRequest passwordRequest) {
        return employeeProfileService.changePasswordProfile(name, passwordRequest);
    }
}
