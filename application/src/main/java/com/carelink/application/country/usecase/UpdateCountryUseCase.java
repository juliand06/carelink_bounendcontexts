package com.carelink.application.country.usecase;

import com.carelink.application.country.command.UpdateCountryCommand;
import com.carelink.application.country.dto.CountryResponse;
import com.carelink.domain.country.port.repository.CountryRepository;
import com.carelink.application.country.exception.CountryNotFoundApplicationException;

public class UpdateCountryUseCase {
    private final CountryRepository repository;

    public UpdateCountryUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public CountryResponse execute(UpdateCountryCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new CountryNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.name(),
                command.isoCode(),
                command.notes(),
                command.enabled(),
                command.dialPrefix());
        var saved = repository.save(aggregate);
        return new CountryResponse(
                saved.id().value(),
                saved.name(),
                saved.isoCode(),
                saved.notes(),
                saved.enabled(),
                saved.dialPrefix(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
