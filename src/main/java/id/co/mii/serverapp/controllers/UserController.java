package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.UserEmployeeRequest;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserEmployeeRequest userEmployeeRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(userEmployeeRequest));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody UserEmployeeRequest userEmployeeRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.update(id, userEmployeeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.delete(id));
    }
}
