package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.requests.UserEmployeeRequest;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody UserEmployeeRequest userEmployeeRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.create(userEmployeeRequest));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody UserEmployeeRequest userEmployeeRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.update(id, userEmployeeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.delete(id));
    }
}
