package com.carelink.infrastructure.handoff.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateHandoffRequest(
        @NotNull(message = "conversationId is required")
        UUID conversationId,

        @NotNull(message = "handoffStatusId is required")
        UUID handoffStatusId,

        @NotNull(message = "originatedFromAi is required")
        Boolean originatedFromAi,

        String handoffReason
) {
}
