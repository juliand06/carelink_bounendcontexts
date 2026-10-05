package com.carelink.application.country.usecase;

import com.carelink.application.country.dto.CountryResponse;
import com.carelink.domain.country.port.repository.CountryRepository;
import com.carelink.application.country.exception.CountryNotFoundApplicationException;
import com.carelink.domain.country.model.valueobject.CountryId;

public class GetCountryByIdUseCase {
    private final CountryRepository repository;

    public GetCountryByIdUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public CountryResponse execute(CountryId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CountryNotFoundApplicationException(id.value().toString()));
        return new CountryResponse(
                aggregate.id().value(),
                aggregate.name(),
                aggregate.isoCode(),
                aggregate.notes(),
                aggregate.enabled(),
                aggregate.dialPrefix(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
