package com.carelink.application.handoff.usecase;

import java.time.LocalDateTime;

import com.carelink.application.handoff.exception.HandoffNotFoundApplicationException;
import com.carelink.domain.handoff.event.HandoffDeletedEvent;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoff.port.repository.HandoffRepository;

public class DeleteHandoffUseCase {
    private final HandoffRepository repository;

    public DeleteHandoffUseCase(HandoffRepository repository) {
        this.repository = repository;
    }

    public HandoffDeletedEvent execute(HandoffId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HandoffNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new HandoffDeletedEvent(id, LocalDateTime.now());
    }
}
