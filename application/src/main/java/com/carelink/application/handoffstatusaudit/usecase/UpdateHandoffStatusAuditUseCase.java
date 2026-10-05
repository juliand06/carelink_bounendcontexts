package com.carelink.application.handoffstatusaudit.usecase;

import com.carelink.application.handoffstatusaudit.command.UpdateHandoffStatusAuditCommand;
import com.carelink.application.handoffstatusaudit.dto.HandoffStatusAuditResponse;
import com.carelink.domain.handoffstatusaudit.port.repository.HandoffStatusAuditRepository;
import com.carelink.application.handoffstatusaudit.exception.HandoffStatusAuditNotFoundApplicationException;

public class UpdateHandoffStatusAuditUseCase {
    private final HandoffStatusAuditRepository repository;

    public UpdateHandoffStatusAuditUseCase(HandoffStatusAuditRepository repository) {
        this.repository = repository;
    }

    public HandoffStatusAuditResponse execute(UpdateHandoffStatusAuditCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new HandoffStatusAuditNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.handoffId(),
                command.handoffStatusId());
        var saved = repository.save(aggregate);
        return new HandoffStatusAuditResponse(
                saved.id().value(),
                saved.handoffId().value(),
                saved.handoffStatusId().value(),
                saved.recordedAt(),
                saved.transitionedAt());
    }
}
