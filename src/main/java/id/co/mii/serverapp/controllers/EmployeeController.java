package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.EmployeeRequest;
import id.co.mii.serverapp.models.dto.requests.NewEmployeeRequest;
import id.co.mii.serverapp.models.dto.requests.PasswordRequest;
import id.co.mii.serverapp.models.dto.requests.VerifyRequest;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(
        value = {"/employee", "/registration"},
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee create(@RequestBody NewEmployeeRequest request) {
        return employeeService.create(request);
    }

    @PostMapping(
        value = "/employee-verification",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView verify(@ModelAttribute VerifyRequest request
    ) {
        ModelAndView modelAndView;

        try {
            employeeService.verify(request);
            modelAndView = new ModelAndView("views/employee_verification_success");
        } catch (Exception exception) {
            modelAndView = new ModelAndView("redirect:/employee-verification?token=" + request.getToken());
            modelAndView.addObject("message", exception.getMessage());
        }

        return modelAndView;
    }

    @GetMapping(
        value = "/employee-verification",
        produces = MediaType.TEXT_HTML_VALUE
    )
    public ModelAndView verify(@RequestParam(name = "token") String token) {
        ModelAndView modelAndView;

        try {
            Employee employee = employeeService.getByToken(token);
            boolean isExpired = LocalDateTime.now().isAfter(employee.getUser().getTokenExpiredAt());

            if (employee.getUser().getIsEnabled() || isExpired) {
                modelAndView = new ModelAndView("views/employee_verification_not_found");
            } else {
                modelAndView = new ModelAndView("views/employee_verification_form");
                modelAndView.addObject("employee", employee);
            }
        } catch (Exception exception) {
            modelAndView = new ModelAndView("views/employee_verification_not_found");
        }

        return modelAndView;
    }

    @GetMapping(
        value = "/employee-dashboard",
        produces = MediaType.TEXT_HTML_VALUE
    )
    public ModelAndView dashboard() {
        return new ModelAndView("views/employee_dashboard");
    }

    @GetMapping(
        value = "/employee/session",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee getEmployeeBySession() {
        return employeeService.getEmployeeBySession();
    }

    @GetMapping(
        value = "/employee/{employeeId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee getById(@PathVariable Integer employeeId) {
        return employeeService.getById(employeeId);
    }

    @GetMapping(
        value = {"/employee", "/employees"},
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @PutMapping(
        value = "/employee",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee update(@RequestBody EmployeeRequest request) {
        return employeeService.update(request);
    }

    @PutMapping(
        value = "/employee/password",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee update(@RequestBody PasswordRequest request) {
        return employeeService.update(request);
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
}
