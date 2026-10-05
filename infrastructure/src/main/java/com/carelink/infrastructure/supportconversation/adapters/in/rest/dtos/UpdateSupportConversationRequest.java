package com.carelink.infrastructure.supportconversation.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateSupportConversationRequest(
        @NotNull(message = "conversationStatusId is required")
        UUID conversationStatusId,

        @NotNull(message = "urgencyLevelId is required")
        UUID urgencyLevelId,

        LocalDateTime lastInteractionAt,

        @NotNull(message = "isClosed is required")
        Boolean isClosed,

        LocalDateTime closedAt,

        UUID closedBy
) {
}
