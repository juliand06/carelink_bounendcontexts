package com.carelink.application.appointmentstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentStatusResponse(
        UUID id,
        String code,
        String label,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
