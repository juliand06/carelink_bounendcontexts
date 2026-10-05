package com.carelink.application.handoffstatusaudit.command;

import java.util.Objects;

import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;

public record UpdateHandoffStatusAuditCommand(
        HandoffStatusAuditId id,
        HandoffId handoffId,
        HandoffStatusId handoffStatusId
) {
    public UpdateHandoffStatusAuditCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(handoffId, "handoffId must not be null");
        Objects.requireNonNull(handoffStatusId, "handoffStatusId must not be null");
    }
}
