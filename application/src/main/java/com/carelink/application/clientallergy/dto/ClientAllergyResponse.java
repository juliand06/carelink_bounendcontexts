package com.carelink.application.clientallergy.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientAllergyResponse(
        UUID id,
        UUID clientId,
        String allergen,
        String reactionDescription,
        String severityLevel,
        Boolean active,
        LocalDateTime registeredAt,
        UUID registeredBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
