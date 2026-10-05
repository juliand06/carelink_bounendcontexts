package com.carelink.application.handoffstatusaudit.usecase;

import java.time.LocalDateTime;

import com.carelink.application.handoffstatusaudit.exception.HandoffStatusAuditNotFoundApplicationException;
import com.carelink.domain.handoffstatusaudit.event.HandoffStatusAuditDeletedEvent;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;
import com.carelink.domain.handoffstatusaudit.port.repository.HandoffStatusAuditRepository;

public class DeleteHandoffStatusAuditUseCase {
    private final HandoffStatusAuditRepository repository;

    public DeleteHandoffStatusAuditUseCase(HandoffStatusAuditRepository repository) {
        this.repository = repository;
    }

    public HandoffStatusAuditDeletedEvent execute(HandoffStatusAuditId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HandoffStatusAuditNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new HandoffStatusAuditDeletedEvent(id, LocalDateTime.now());
    }
}
