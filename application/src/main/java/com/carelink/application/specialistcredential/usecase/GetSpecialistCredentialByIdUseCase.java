package com.carelink.application.specialistcredential.usecase;

import com.carelink.application.specialistcredential.dto.SpecialistCredentialResponse;
import com.carelink.domain.specialistcredential.port.repository.SpecialistCredentialRepository;
import com.carelink.application.specialistcredential.exception.SpecialistCredentialNotFoundApplicationException;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;

public class GetSpecialistCredentialByIdUseCase {
    private final SpecialistCredentialRepository repository;

    public GetSpecialistCredentialByIdUseCase(SpecialistCredentialRepository repository) {
        this.repository = repository;
    }

    public SpecialistCredentialResponse execute(SpecialistCredentialId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SpecialistCredentialNotFoundApplicationException(id.value().toString()));
        return new SpecialistCredentialResponse(
                aggregate.id().value(),
                aggregate.academicProgramId().value(),
                aggregate.specialistId().value(),
                aggregate.degreeTitle(),
                aggregate.institution(),
                aggregate.isVerified(),
                aggregate.resolutionCode(),
                aggregate.countryId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
