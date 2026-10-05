package com.carelink.application.municipality.usecase;

import com.carelink.application.municipality.command.RegisterMunicipalityCommand;
import com.carelink.application.municipality.dto.MunicipalityResponse;
import com.carelink.domain.municipality.port.repository.MunicipalityRepository;
import com.carelink.domain.municipality.model.aggregate.Municipality;

public class RegisterMunicipalityUseCase {
    private final MunicipalityRepository repository;

    public RegisterMunicipalityUseCase(MunicipalityRepository repository) {
        this.repository = repository;
    }

    public MunicipalityResponse execute(RegisterMunicipalityCommand command) {
        Municipality aggregate = Municipality.register(
                command.name(),
                command.isoCode(),
                command.notes(),
                command.enabled(),
                command.departmentId());
        Municipality saved = repository.save(aggregate);
        return new MunicipalityResponse(
                saved.id().value(),
                saved.name(),
                saved.isoCode(),
                saved.notes(),
                saved.enabled(),
                saved.departmentId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
