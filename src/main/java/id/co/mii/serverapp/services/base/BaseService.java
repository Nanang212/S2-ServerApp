package id.co.mii.serverapp.services.base;

import id.co.mii.serverapp.dto.base.BaseDto;
import id.co.mii.serverapp.models.base.BaseEntity;
import id.co.mii.serverapp.repositories.base.BaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Primary
public class BaseService<E extends BaseEntity> {
    @Autowired
    private BaseRepository<E> repository;
    @Autowired
    private ModelMapper mapper;

    public List<E> getAll() {
        return repository.findAll();
    }

    public E getById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity is not found"));
    }

    public E create(E entity) {
        return repository.save(entity);
    }

    public E update(Integer id, E entity) {
        getById(id);
        entity.setId(id);
        return repository.save(entity);
    }

    public E delete(Integer id) {
        E entity = getById(id);
        repository.delete(entity);
        return entity;
    }
}
