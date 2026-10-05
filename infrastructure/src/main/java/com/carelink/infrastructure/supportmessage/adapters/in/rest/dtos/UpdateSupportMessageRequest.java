package com.carelink.infrastructure.supportmessage.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateSupportMessageRequest(
        @NotNull(message = "conversationId is required")
        UUID conversationId,

        @NotNull(message = "messageFormatId is required")
        UUID messageFormatId,

        @NotNull(message = "memberId is required")
        UUID memberId,

        @NotNull(message = "payload is required")
        String payload,

        String extraData
) {
}
