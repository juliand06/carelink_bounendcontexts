package com.carelink.infrastructure.conversationmember.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateConversationMemberRequest(
        @NotNull(message = "conversationId is required")
        UUID conversationId,

        UUID clientId,

        UUID specialistId,

        @NotNull(message = "senderRoleId is required")
        UUID senderRoleId
) {
}
