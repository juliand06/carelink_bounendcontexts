package com.carelink.application.trustedcontact.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TrustedContactResponse(
        UUID id,
        String fullName,
        UUID relationshipTypeId,
        Boolean isPrimaryContact,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
