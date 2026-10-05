package com.carelink.application.specialistcredential.usecase;

import com.carelink.application.specialistcredential.command.RegisterSpecialistCredentialCommand;
import com.carelink.application.specialistcredential.dto.SpecialistCredentialResponse;
import com.carelink.domain.specialistcredential.port.repository.SpecialistCredentialRepository;
import com.carelink.domain.specialistcredential.model.aggregate.SpecialistCredential;

public class RegisterSpecialistCredentialUseCase {
    private final SpecialistCredentialRepository repository;

    public RegisterSpecialistCredentialUseCase(SpecialistCredentialRepository repository) {
        this.repository = repository;
    }

    public SpecialistCredentialResponse execute(RegisterSpecialistCredentialCommand command) {
        SpecialistCredential aggregate = SpecialistCredential.register(
                command.academicProgramId(),
                command.specialistId(),
                command.degreeTitle(),
                command.institution(),
                command.isVerified(),
                command.resolutionCode(),
                command.countryId());
        SpecialistCredential saved = repository.save(aggregate);
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
