package com.carelink.application.appointmentmodality.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentModalityResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
