package com.carelink.application.handoffstatusaudit.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record HandoffStatusAuditResponse(
        UUID id,
        UUID handoffId,
        UUID handoffStatusId,
        LocalDateTime recordedAt,
        LocalDateTime transitionedAt
) {
}
