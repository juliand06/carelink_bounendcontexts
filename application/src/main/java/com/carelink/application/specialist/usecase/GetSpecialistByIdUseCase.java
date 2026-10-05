package com.carelink.application.specialist.usecase;

import com.carelink.application.specialist.dto.SpecialistResponse;
import com.carelink.domain.specialist.port.repository.SpecialistRepository;
import com.carelink.application.specialist.exception.SpecialistNotFoundApplicationException;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class GetSpecialistByIdUseCase {
    private final SpecialistRepository repository;

    public GetSpecialistByIdUseCase(SpecialistRepository repository) {
        this.repository = repository;
    }

    public SpecialistResponse execute(SpecialistId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SpecialistNotFoundApplicationException(id.value().toString()));
        return new SpecialistResponse(
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
                aggregate.updatedAt());
    }
}
