package com.carelink.application.department.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DepartmentResponse(
        UUID id,
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        UUID countryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
