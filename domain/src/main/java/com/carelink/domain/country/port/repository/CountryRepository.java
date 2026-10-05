package com.carelink.domain.country.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.country.model.aggregate.Country;
import com.carelink.domain.country.model.valueobject.CountryId;

public interface CountryRepository {
    Country save(Country aggregate);

    Optional<Country> findById(CountryId id);

    List<Country> findAll();

    void delete(Country aggregate);
}
