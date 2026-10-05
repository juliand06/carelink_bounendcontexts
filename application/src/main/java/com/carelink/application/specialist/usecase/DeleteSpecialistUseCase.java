package com.carelink.application.specialist.usecase;

import java.time.LocalDateTime;

import com.carelink.application.specialist.exception.SpecialistNotFoundApplicationException;
import com.carelink.domain.specialist.event.SpecialistDeletedEvent;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialist.port.repository.SpecialistRepository;

public class DeleteSpecialistUseCase {
    private final SpecialistRepository repository;

    public DeleteSpecialistUseCase(SpecialistRepository repository) {
        this.repository = repository;
    }

    public SpecialistDeletedEvent execute(SpecialistId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SpecialistNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new SpecialistDeletedEvent(id, LocalDateTime.now());
    }
}
