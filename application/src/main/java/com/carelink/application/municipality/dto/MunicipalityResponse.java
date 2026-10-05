package com.carelink.application.municipality.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MunicipalityResponse(
        UUID id,
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        UUID departmentId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
