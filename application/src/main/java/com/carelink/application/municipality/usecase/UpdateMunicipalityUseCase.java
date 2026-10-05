package com.carelink.application.municipality.usecase;

import com.carelink.application.municipality.command.UpdateMunicipalityCommand;
import com.carelink.application.municipality.dto.MunicipalityResponse;
import com.carelink.domain.municipality.port.repository.MunicipalityRepository;
import com.carelink.application.municipality.exception.MunicipalityNotFoundApplicationException;

public class UpdateMunicipalityUseCase {
    private final MunicipalityRepository repository;

    public UpdateMunicipalityUseCase(MunicipalityRepository repository) {
        this.repository = repository;
    }

    public MunicipalityResponse execute(UpdateMunicipalityCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new MunicipalityNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.name(),
                command.isoCode(),
                command.notes(),
                command.enabled(),
                command.departmentId());
        var saved = repository.save(aggregate);
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
