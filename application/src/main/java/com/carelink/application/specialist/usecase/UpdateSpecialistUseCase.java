package com.carelink.application.specialist.usecase;

import com.carelink.application.specialist.command.UpdateSpecialistCommand;
import com.carelink.application.specialist.dto.SpecialistResponse;
import com.carelink.domain.specialist.port.repository.SpecialistRepository;
import com.carelink.application.specialist.exception.SpecialistNotFoundApplicationException;

public class UpdateSpecialistUseCase {
    private final SpecialistRepository repository;

    public UpdateSpecialistUseCase(SpecialistRepository repository) {
        this.repository = repository;
    }

    public SpecialistResponse execute(UpdateSpecialistCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SpecialistNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.idDocumentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.specialistRoleId(),
                command.professionalLicense(),
                command.active(),
                command.municipalityId());
        var saved = repository.save(aggregate);
        return new SpecialistResponse(
                saved.id().value(),
                saved.idDocumentTypeId().value(),
                saved.documentNumber(),
                saved.firstName(),
                saved.lastName(),
                saved.specialistRoleId().value(),
                saved.professionalLicense(),
                saved.active(),
                saved.municipalityId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
