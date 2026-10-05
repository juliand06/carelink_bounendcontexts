package com.carelink.application.handoffstatus.usecase;

import java.time.LocalDateTime;

import com.carelink.application.handoffstatus.exception.HandoffStatusNotFoundApplicationException;
import com.carelink.domain.handoffstatus.event.HandoffStatusDeletedEvent;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatus.port.repository.HandoffStatusRepository;

public class DeleteHandoffStatusUseCase {
    private final HandoffStatusRepository repository;

    public DeleteHandoffStatusUseCase(HandoffStatusRepository repository) {
        this.repository = repository;
    }

    public HandoffStatusDeletedEvent execute(HandoffStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HandoffStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new HandoffStatusDeletedEvent(id, LocalDateTime.now());
    }
}
