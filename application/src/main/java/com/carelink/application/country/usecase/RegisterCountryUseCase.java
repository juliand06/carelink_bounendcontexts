package com.carelink.application.country.usecase;

import com.carelink.application.country.command.RegisterCountryCommand;
import com.carelink.application.country.dto.CountryResponse;
import com.carelink.domain.country.port.repository.CountryRepository;
import com.carelink.domain.country.model.aggregate.Country;

public class RegisterCountryUseCase {
    private final CountryRepository repository;

    public RegisterCountryUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public CountryResponse execute(RegisterCountryCommand command) {
        Country aggregate = Country.register(
                command.name(),
                command.isoCode(),
                command.notes(),
                command.enabled(),
                command.dialPrefix());
        Country saved = repository.save(aggregate);
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
