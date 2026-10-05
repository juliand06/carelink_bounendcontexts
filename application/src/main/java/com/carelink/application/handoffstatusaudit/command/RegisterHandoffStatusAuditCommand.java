package com.carelink.application.handoffstatusaudit.command;

import java.util.Objects;

import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;

public record RegisterHandoffStatusAuditCommand(
        HandoffId handoffId,
        HandoffStatusId handoffStatusId
) {
    public RegisterHandoffStatusAuditCommand {
        Objects.requireNonNull(handoffId, "handoffId must not be null");
        Objects.requireNonNull(handoffStatusId, "handoffStatusId must not be null");
    }
}
