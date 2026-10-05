package com.carelink.application.appointment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        UUID healthRecordId,
        UUID specialistId,
        UUID appointmentTypeId,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String chiefComplaint,
        String currentCondition,
        UUID modalityId,
        UUID statusId,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy
) {
}
