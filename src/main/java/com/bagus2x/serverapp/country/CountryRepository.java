package com.bagus2x.serverapp.country;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Boolean existsByCodeIgnoreCase(String name);

    Boolean existsByNameIgnoreCase(String name);
}
