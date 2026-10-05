package com.carelink.application.specialist.usecase;

import com.carelink.application.specialist.command.RegisterSpecialistCommand;
import com.carelink.application.specialist.dto.SpecialistResponse;
import com.carelink.domain.specialist.port.repository.SpecialistRepository;
import com.carelink.domain.specialist.model.aggregate.Specialist;

public class RegisterSpecialistUseCase {
    private final SpecialistRepository repository;

    public RegisterSpecialistUseCase(SpecialistRepository repository) {
        this.repository = repository;
    }

    public SpecialistResponse execute(RegisterSpecialistCommand command) {
        Specialist aggregate = Specialist.register(
                command.idDocumentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.specialistRoleId(),
                command.professionalLicense(),
                command.active(),
                command.municipalityId());
        Specialist saved = repository.save(aggregate);
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
