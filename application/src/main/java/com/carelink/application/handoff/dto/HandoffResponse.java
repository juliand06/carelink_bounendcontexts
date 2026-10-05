package com.carelink.application.handoff.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record HandoffResponse(
        UUID id,
        UUID conversationId,
        UUID handoffStatusId,
        Boolean originatedFromAi,
        String handoffReason,
        LocalDateTime createdAt
) {
}
