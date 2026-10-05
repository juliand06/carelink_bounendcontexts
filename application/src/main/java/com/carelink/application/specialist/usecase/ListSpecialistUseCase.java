package com.carelink.application.specialist.usecase;

import java.util.List;

import com.carelink.application.specialist.dto.SpecialistResponse;
import com.carelink.domain.specialist.port.repository.SpecialistRepository;

public class ListSpecialistUseCase {
    private final SpecialistRepository repository;

    public ListSpecialistUseCase(SpecialistRepository repository) {
        this.repository = repository;
    }

    public List<SpecialistResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new SpecialistResponse(
                        aggregate.id().value(),
                        aggregate.idDocumentTypeId().value(),
                        aggregate.documentNumber(),
                        aggregate.firstName(),
                        aggregate.lastName(),
                        aggregate.specialistRoleId().value(),
                        aggregate.professionalLicense(),
                        aggregate.active(),
                        aggregate.municipalityId().value(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
