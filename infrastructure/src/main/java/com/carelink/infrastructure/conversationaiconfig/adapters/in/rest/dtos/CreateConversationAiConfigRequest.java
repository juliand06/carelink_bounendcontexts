package com.carelink.infrastructure.conversationaiconfig.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateConversationAiConfigRequest(
        @NotNull(message = "conversationId is required")
        UUID conversationId,

        @NotNull(message = "aiEnabled is required")
        Boolean aiEnabled,

        @NotNull(message = "preferredModelId is required")
        UUID preferredModelId
) {
}
