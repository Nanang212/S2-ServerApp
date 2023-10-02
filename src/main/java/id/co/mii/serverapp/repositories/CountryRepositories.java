package id.co.mii.serverapp.repositories;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.Country;
// import id.co.mii.serverapp.models.Region;

@Repository
public interface CountryRepositories extends JpaRepository<Country, Integer> {
    public boolean existsByName(String name);
}
