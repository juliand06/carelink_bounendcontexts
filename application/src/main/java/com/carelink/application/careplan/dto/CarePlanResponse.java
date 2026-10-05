package com.carelink.application.careplan.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CarePlanResponse(
        UUID id,
        UUID appointmentId,
        UUID specialistId,
        String planTitle,
        String planDescription,
        LocalDate startDate,
        LocalDate projectedEndDate,
        UUID carePlanStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
