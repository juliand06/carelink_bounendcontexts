package com.carelink.application.handoffstatusaudit.usecase;

import com.carelink.application.handoffstatusaudit.command.RegisterHandoffStatusAuditCommand;
import com.carelink.application.handoffstatusaudit.dto.HandoffStatusAuditResponse;
import com.carelink.domain.handoffstatusaudit.port.repository.HandoffStatusAuditRepository;
import com.carelink.domain.handoffstatusaudit.model.aggregate.HandoffStatusAudit;

public class RegisterHandoffStatusAuditUseCase {
    private final HandoffStatusAuditRepository repository;

    public RegisterHandoffStatusAuditUseCase(HandoffStatusAuditRepository repository) {
        this.repository = repository;
    }

    public HandoffStatusAuditResponse execute(RegisterHandoffStatusAuditCommand command) {
        HandoffStatusAudit aggregate = HandoffStatusAudit.register(
                command.handoffId(),
                command.handoffStatusId());
        HandoffStatusAudit saved = repository.save(aggregate);
        return new HandoffStatusAuditResponse(
                saved.id().value(),
                saved.handoffId().value(),
                saved.handoffStatusId().value(),
                saved.recordedAt(),
                saved.transitionedAt());
    }
}
