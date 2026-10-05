package com.carelink.application.handoffstatusaudit.usecase;

import java.util.List;

import com.carelink.application.handoffstatusaudit.dto.HandoffStatusAuditResponse;
import com.carelink.domain.handoffstatusaudit.port.repository.HandoffStatusAuditRepository;

public class ListHandoffStatusAuditUseCase {
    private final HandoffStatusAuditRepository repository;

    public ListHandoffStatusAuditUseCase(HandoffStatusAuditRepository repository) {
        this.repository = repository;
    }

    public List<HandoffStatusAuditResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new HandoffStatusAuditResponse(
                        aggregate.id().value(),
                        aggregate.handoffId().value(),
                        aggregate.handoffStatusId().value(),
                        aggregate.recordedAt(),
                        aggregate.transitionedAt()))
                .toList();
    }
}
