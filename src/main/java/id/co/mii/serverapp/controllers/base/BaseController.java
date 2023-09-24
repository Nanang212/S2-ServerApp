package id.co.mii.serverapp.controllers.base;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.base.BaseEntity;
import id.co.mii.serverapp.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BaseController<E extends BaseEntity> {
    @Autowired
    private BaseService<E> service;

    @PostMapping
    public ResponseEntity<E> create(@RequestBody E entity) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(entity));
    }

    @GetMapping
    public ResponseEntity<List<E>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> getById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<E> update(@PathVariable Integer id, @RequestBody E entity) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<E> delete(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.delete(id));
    }
}
