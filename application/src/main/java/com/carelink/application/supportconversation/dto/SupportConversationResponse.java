package com.carelink.application.supportconversation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupportConversationResponse(
        UUID id,
        UUID conversationStatusId,
        UUID urgencyLevelId,
        LocalDateTime lastInteractionAt,
        Boolean isClosed,
        LocalDateTime closedAt,
        UUID closedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
