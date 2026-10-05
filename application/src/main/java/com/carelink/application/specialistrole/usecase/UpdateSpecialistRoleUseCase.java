package com.carelink.application.specialistrole.usecase;

import com.carelink.application.specialistrole.command.UpdateSpecialistRoleCommand;
import com.carelink.application.specialistrole.dto.SpecialistRoleResponse;
import com.carelink.domain.specialistrole.port.repository.SpecialistRoleRepository;
import com.carelink.application.specialistrole.exception.SpecialistRoleNotFoundApplicationException;

public class UpdateSpecialistRoleUseCase {
    private final SpecialistRoleRepository repository;

    public UpdateSpecialistRoleUseCase(SpecialistRoleRepository repository) {
        this.repository = repository;
    }

    public SpecialistRoleResponse execute(UpdateSpecialistRoleCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SpecialistRoleNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.title());
        var saved = repository.save(aggregate);
        return new SpecialistRoleResponse(
                saved.id().value(),
                saved.title(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
