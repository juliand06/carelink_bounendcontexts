package com.carelink.application.country.usecase;

import java.util.List;

import com.carelink.application.country.dto.CountryResponse;
import com.carelink.domain.country.port.repository.CountryRepository;

public class ListCountryUseCase {
    private final CountryRepository repository;

    public ListCountryUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public List<CountryResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new CountryResponse(
                        aggregate.id().value(),
                        aggregate.name(),
                        aggregate.isoCode(),
                        aggregate.notes(),
                        aggregate.enabled(),
                        aggregate.dialPrefix(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
