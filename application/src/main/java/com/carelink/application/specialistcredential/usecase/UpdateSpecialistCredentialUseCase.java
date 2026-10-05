package com.carelink.application.specialistcredential.usecase;

import com.carelink.application.specialistcredential.command.UpdateSpecialistCredentialCommand;
import com.carelink.application.specialistcredential.dto.SpecialistCredentialResponse;
import com.carelink.domain.specialistcredential.port.repository.SpecialistCredentialRepository;
import com.carelink.application.specialistcredential.exception.SpecialistCredentialNotFoundApplicationException;

public class UpdateSpecialistCredentialUseCase {
    private final SpecialistCredentialRepository repository;

    public UpdateSpecialistCredentialUseCase(SpecialistCredentialRepository repository) {
        this.repository = repository;
    }

    public SpecialistCredentialResponse execute(UpdateSpecialistCredentialCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SpecialistCredentialNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.academicProgramId(),
                command.specialistId(),
                command.degreeTitle(),
                command.institution(),
                command.isVerified(),
                command.resolutionCode(),
                command.countryId());
        var saved = repository.save(aggregate);
        return new SpecialistCredentialResponse(
                saved.id().value(),
                saved.academicProgramId().value(),
                saved.specialistId().value(),
                saved.degreeTitle(),
                saved.institution(),
                saved.isVerified(),
                saved.resolutionCode(),
                saved.countryId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
