package id.co.mii.serverapp.repositories.base;

import id.co.mii.serverapp.models.base.BaseEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Integer> {
}
