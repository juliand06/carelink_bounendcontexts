package com.carelink.application.senderrole.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SenderRoleResponse(
        UUID id,
        String label,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
