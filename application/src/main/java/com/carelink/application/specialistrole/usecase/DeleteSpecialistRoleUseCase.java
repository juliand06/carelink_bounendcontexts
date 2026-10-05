package com.carelink.application.specialistrole.usecase;

import java.time.LocalDateTime;

import com.carelink.application.specialistrole.exception.SpecialistRoleNotFoundApplicationException;
import com.carelink.domain.specialistrole.event.SpecialistRoleDeletedEvent;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import com.carelink.domain.specialistrole.port.repository.SpecialistRoleRepository;

public class DeleteSpecialistRoleUseCase {
    private final SpecialistRoleRepository repository;

    public DeleteSpecialistRoleUseCase(SpecialistRoleRepository repository) {
        this.repository = repository;
    }

    public SpecialistRoleDeletedEvent execute(SpecialistRoleId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SpecialistRoleNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new SpecialistRoleDeletedEvent(id, LocalDateTime.now());
    }
}
