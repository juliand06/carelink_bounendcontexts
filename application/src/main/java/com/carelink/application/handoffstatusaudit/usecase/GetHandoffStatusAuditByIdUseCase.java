package com.carelink.application.handoffstatusaudit.usecase;

import com.carelink.application.handoffstatusaudit.dto.HandoffStatusAuditResponse;
import com.carelink.domain.handoffstatusaudit.port.repository.HandoffStatusAuditRepository;
import com.carelink.application.handoffstatusaudit.exception.HandoffStatusAuditNotFoundApplicationException;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;

public class GetHandoffStatusAuditByIdUseCase {
    private final HandoffStatusAuditRepository repository;

    public GetHandoffStatusAuditByIdUseCase(HandoffStatusAuditRepository repository) {
        this.repository = repository;
    }

    public HandoffStatusAuditResponse execute(HandoffStatusAuditId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HandoffStatusAuditNotFoundApplicationException(id.value().toString()));
        return new HandoffStatusAuditResponse(
                aggregate.id().value(),
                aggregate.handoffId().value(),
                aggregate.handoffStatusId().value(),
                aggregate.recordedAt(),
                aggregate.transitionedAt());
    }
}
