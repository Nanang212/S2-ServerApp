package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;
import id.co.mii.serverapp.models.dto.requests.NewEmployeeRequest;
import id.co.mii.serverapp.models.dto.requests.VerifyRequest;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(
        value = "/employee",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody EmployeeRequest request) {
        return employeeService.create(request);
    }

    @GetMapping(
        value = "/employee/{employeeId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee getById(@PathVariable Integer employeeId) {
        return employeeService.getById(employeeId);
    }

    @GetMapping(
        value = "/employees",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @PutMapping(
        value = "/employee/{employeeId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee update(@PathVariable Integer employeeId, @RequestBody EmployeeRequest request) {
        return employeeService.update(employeeId, request);
    }

    @DeleteMapping(
        value = "/employee/{employeeId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee delete(@PathVariable Integer employeeId) {
        return employeeService.delete(employeeId);
    }

    @PostMapping("/employee-registration")
    public Employee register(@RequestBody NewEmployeeRequest request) {
        return employeeService.register(request);
    }

    @GetMapping("/employee-verification")
    public ModelAndView verify(@RequestParam(name = "token") String token) {
        ModelAndView modelAndView;

        try {
            Employee employee = employeeService.getByToken(token);

            if (employee.getUser().getIsEnabled()) {
                modelAndView = new ModelAndView("views/employee_verification_not_found");
            } else {
                modelAndView = new ModelAndView("views/employee_verification_form");
                modelAndView.addObject("token", token);
            }
        } catch (Exception exception) {
            modelAndView = new ModelAndView("views/employee_verification_not_found");
        }

        return modelAndView;
    }

    @PostMapping("/employee-verification")
    public ModelAndView verify(@ModelAttribute VerifyRequest request) {
        try {
            employeeService.verify(request);
            return new ModelAndView("views/employee_verification_success");
        } catch (Exception e) {
            return new ModelAndView("views/employee_verification_not_found");
        }
    }
}
