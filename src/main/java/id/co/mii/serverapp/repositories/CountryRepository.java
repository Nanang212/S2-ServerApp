package id.co.mii.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.co.mii.serverapp.models.Country;
// mengatur query database --> menjalankan CRUD
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    public boolean existsByName(String name);
}
