package com.carelink.application.specialistrole.usecase;

import com.carelink.application.specialistrole.dto.SpecialistRoleResponse;
import com.carelink.domain.specialistrole.port.repository.SpecialistRoleRepository;
import com.carelink.application.specialistrole.exception.SpecialistRoleNotFoundApplicationException;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;

public class GetSpecialistRoleByIdUseCase {
    private final SpecialistRoleRepository repository;

    public GetSpecialistRoleByIdUseCase(SpecialistRoleRepository repository) {
        this.repository = repository;
    }

    public SpecialistRoleResponse execute(SpecialistRoleId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SpecialistRoleNotFoundApplicationException(id.value().toString()));
        return new SpecialistRoleResponse(
                aggregate.id().value(),
                aggregate.title(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
