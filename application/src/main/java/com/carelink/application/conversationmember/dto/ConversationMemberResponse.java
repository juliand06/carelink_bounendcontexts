package com.carelink.application.conversationmember.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConversationMemberResponse(
        UUID id,
        UUID conversationId,
        UUID clientId,
        UUID specialistId,
        UUID senderRoleId,
        LocalDateTime joinedAt
) {
}
