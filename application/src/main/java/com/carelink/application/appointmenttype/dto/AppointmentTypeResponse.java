package com.carelink.application.appointmenttype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentTypeResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
