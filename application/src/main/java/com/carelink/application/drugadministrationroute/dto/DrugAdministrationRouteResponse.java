package com.carelink.application.drugadministrationroute.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DrugAdministrationRouteResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
