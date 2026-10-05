package com.carelink.application.country.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CountryResponse(
        UUID id,
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        String dialPrefix,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
