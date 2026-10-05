package com.carelink.application.specialistcredential.usecase;

import java.util.List;

import com.carelink.application.specialistcredential.dto.SpecialistCredentialResponse;
import com.carelink.domain.specialistcredential.port.repository.SpecialistCredentialRepository;

public class ListSpecialistCredentialUseCase {
    private final SpecialistCredentialRepository repository;

    public ListSpecialistCredentialUseCase(SpecialistCredentialRepository repository) {
        this.repository = repository;
    }

    public List<SpecialistCredentialResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new SpecialistCredentialResponse(
                        aggregate.id().value(),
                        aggregate.academicProgramId().value(),
                        aggregate.specialistId().value(),
                        aggregate.degreeTitle(),
                        aggregate.institution(),
                        aggregate.isVerified(),
                        aggregate.resolutionCode(),
                        aggregate.countryId().value(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
