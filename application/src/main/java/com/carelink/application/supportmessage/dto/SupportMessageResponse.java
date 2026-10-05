package com.carelink.application.supportmessage.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupportMessageResponse(
        UUID id,
        UUID conversationId,
        UUID messageFormatId,
        UUID memberId,
        String payload,
        String extraData,
        LocalDateTime createdAt
) {
}
